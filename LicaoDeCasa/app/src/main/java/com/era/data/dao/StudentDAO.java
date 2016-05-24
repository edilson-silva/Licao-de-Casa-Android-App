package com.era.data.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.era.data.connection.webserver.GetSetDataWeb;
import com.era.data.connection.webserver.GetSetDataWebInterface;
import com.era.data.interfaces.StudentInterface;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * Created by Edilson on 02/03/2016.
 */
public class StudentDAO implements StudentInterface, GetSetDataWebInterface {

    private Context context;
    private RequestBody formBody;

    //Objeto relativo a conexao com a internet, recebe os dados ainda na thread de UI, sendo ele uma extensao de AsyncTask.
    GetSetDataWeb getSetDataWeb;

    public StudentDAO(Context context) {
        //Construtor vazio somente para instancias padroes do usuario;
        this.context = context;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void selectExternalAllStudents(String urlComplement, Context activityContext, String flagMethodIndicator, boolean showProgressDialog, String dialogMessage) {
        formBody = null;
        formBody = new FormBody.Builder().build();

        GetSetDataWeb getSetDataWeb = new GetSetDataWeb(this, urlComplement, flagMethodIndicator, showProgressDialog, activityContext, dialogMessage);
        Log.i("Student-GetAllStudents", "Pegandoo todos os estudantes");
        getSetDataWeb.execute(formBody);
    }

    @Override
    public void getServerData(String flagMethodIndicator, String serverAnswer) {
        Log.i("Server Answer-I", ""+serverAnswer);
    }
}
