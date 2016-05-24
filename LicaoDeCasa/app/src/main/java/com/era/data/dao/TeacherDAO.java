package com.era.data.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.era.data.bean.Student;
import com.era.data.connection.webserver.CustomJsonArrayRequest;
import com.era.data.connection.webserver.GetSetDataWeb;
import com.era.data.connection.webserver.GetSetDataWebInterface;
import com.era.data.connection.webserver.WebClient;
import com.era.data.interfaces.TeacherInterface;
import com.era.extras.AppConstants;
import com.era.fragments.TeacherFragmentListStudents;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * Created by Edilson on 05/03/2016.
 */
public class TeacherDAO implements TeacherInterface, GetSetDataWebInterface {

    private static List<Student> mStudents = null;
    private static List<Student> myStudents = null;

    public static List<Student> getmStudents() {
        return mStudents;
    }

    public static void setmStudents(List<Student> mStudents) {
        TeacherDAO.mStudents = mStudents;
    }

    private Context context;
    private RequestBody formBody;

    //VOLLEY
    private RequestQueue rq;
    private Map<String, String> mParams;

    String mUrl = null;

    //Objeto relativo a conexao com a internet, recebe os dados ainda na thread de UI, sendo ele uma extensao de AsyncTask.
    GetSetDataWeb getSetDataWeb;

    public TeacherDAO(Context context) {
        //Construtor vazio somente para instancias padroes do usuario;
        this.context = context;
        // INICIALIZANDO O REQUESTQUEUE
        rq = Volley.newRequestQueue(context);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void getMyStudents(String urlComplement, Context activityContext, String flagMethodIndicator, boolean showProgressDialog, String dialogMessage) {
        formBody = null;
        formBody = new FormBody.Builder().build();

        GetSetDataWeb getSetDataWeb = new GetSetDataWeb(this, urlComplement, flagMethodIndicator, showProgressDialog, activityContext, dialogMessage);
        Log.i("Student-GetAllStudents", "Pegandoo todos os estudantes");
        getSetDataWeb.execute(formBody);
    }

    @Override
    public void getServerData(String flagMethodIndicator, String serverAnswer) {
        Log.i("Server Answer", "TDAO:" + serverAnswer);
        JSONArray jsonArray = null;
        JSONObject jsonObject = null;

        if (!serverAnswer.equals(null) && flagMethodIndicator.equals(AppConstants.TEACHERDAO_EXTERNAL_GET_MY_STUDENTS.toString())) {

            try {
                jsonArray = new JSONArray(serverAnswer);
                List<Student> listOfStudents = new ArrayList<>();

                Student student = new Student();
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    student.setsId(jsonObject.getLong("id"));
                    Log.i("Dados", "id: " + jsonObject.getInt("id"));
                    student.setsData(jsonObject.getString("dados"));
                    student.setuName(jsonObject.getString("nome"));
                    student.setuEmail(jsonObject.getString("email"));
                    student.setsAverage(jsonObject.getInt("media"));
                    listOfStudents.add(student);
                }
                Log.i("Usuarios ID", "ID Ultimo da Lista" + listOfStudents.get(listOfStudents.size() - 1));

                // Chamando o metodo para add os Students e exibir a listta.
                TeacherFragmentListStudents tfListStudents = new TeacherFragmentListStudents();
                //tfListStudents.setStudentListAdapter(listOfStudents); //Lembrar de ver o que tinha aqui

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    // TESTE DATA USING VOLLEY
    // METODO PARA TESTE DE RETORNO DO SERVIDOR
    public List<Student> getMyStudents2(Map<String, String> params) {
        mUrl = AppConstants.SERVER_URL.toString() + AppConstants.TEACHERDAO_EXTERNAL_GET_MY_STUDENTS.toString();
        myStudents = new ArrayList<>();

        CustomJsonArrayRequest request = new CustomJsonArrayRequest(Request.Method.POST,
                mUrl,        // URL a ser enviados ou requititados os dados.
                mParams,    //Parametros a ser passado via POST
                new Response.Listener<JSONArray>() {    // Listener para resposta OK, StatusCode = 200
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i(AppConstants.LOG_KEY_SERVER_RETURN.toString(), "onResponse: " + response.toString());
                        if (response != null) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject jo = response.getJSONObject(i);
                                    Log.i(AppConstants.LOG_KEY_SERVER_RETURN.toString(), "onResponseJSONObj: " + jo.toString());
                                    Student student = new Student();
                                    student.setsId(jo.getLong("id"));
                                    student.setsData(jo.getString("dados"));
                                    student.setuName(jo.getString("nome"));
                                    student.setuEmail(jo.getString("email"));
                                    student.setsAverage(jo.getDouble("media"));
                                    myStudents.add(student);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        setmStudents(myStudents);
                    }
                },
                new Response.ErrorListener() {   // Listener para resposta ERROR, StatusCode = 404, etc.
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i(AppConstants.LOG_KEY.toString(), "VolleyError" + AppConstants.LOG_KEY_ERROR.toString() + error.getMessage());
                    }
                }
        );


        // Limitando o request a uma chamada por vez e com 6 segundos de espera para pegar os dados.
        // Wait 20 seconds and don't retry more than once
        request.setRetryPolicy(new DefaultRetryPolicy(5000,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        request.setTag("customRequest");
        rq.add(request);

        return mStudents;
    }

    public List<Student> getMyStudents3() {

        myStudents = new ArrayList<>();

        //GenericMethods.showProgressDialog(context, "Carragdno lista de Estudantes...\nPorfavor, aguarde!","");

        WebClient.get(AppConstants.TEACHER_DAO_GET_MY_STUDENTS.toString(), null, new JsonHttpResponseHandler() {

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] header, JSONArray students) {
                // Pull out the first event on the public timeline
                JSONArray jaStudent = null;
                jaStudent = students;

                Log.i(AppConstants.LOG_KEY_SERVER_RETURN.toString(), "onResponse: " + jaStudent.toString());
                if (jaStudent != null) {
                    for (int i = 0; i < jaStudent.length(); i++) {
                        try {
                            JSONObject jo = jaStudent.getJSONObject(i);
                            Log.i(AppConstants.LOG_KEY_SERVER_RETURN.toString(), "onResponseJSONObj: " + jo.toString());
                            Student student = new Student();
                            student.setsId(jo.getLong("id"));
                            student.setsData(jo.getString("dados"));
                            student.setuName(jo.getString("nome"));
                            student.setuEmail(jo.getString("email"));
                            student.setsAverage(jo.getDouble("media"));
                            myStudents.add(student);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                if (myStudents.size() > 0) {
                    System.out.println("Usuario 1: " + myStudents.get(0).getuName());
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable error) {
                Log.i(AppConstants.LOG_KEY.toString(), "VolleyError" + AppConstants.LOG_KEY_ERROR.toString() + error.getMessage());
            }
        });

        return myStudents;
    }

}
