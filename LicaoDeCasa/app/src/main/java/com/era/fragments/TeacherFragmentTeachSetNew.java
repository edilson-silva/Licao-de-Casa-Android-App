package com.era.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.era.extras.AppConstants;
import com.era.extras.GenericMethods;
import com.era.licaodecasa.R;
import com.era.licaodecasa.api.TeacherAPI;
import com.era.licaodecasa.model.pojo.Curso;
import com.era.licaodecasa.model.pojo.Disciplina;
import com.era.licaodecasa.model.pojo.Escola;
import com.era.licaodecasa.model.pojo.Turma;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TeacherFragmentTeachSetNew extends Fragment{

    // SPINNERS
	private Spinner mSPSchools;
    private Spinner mSPCourses;
    private Spinner mSPTeams;
    private Spinner mSPDisciplines;
    // ESCOLAS
    private List<Escola> mListSchools = new ArrayList<>();
    private ArrayList<String> mSchoolsItems;
    // CURSOS - DEPENDE DA ESCOLA
    private List<Curso> mListCourses = new ArrayList<>();
    private ArrayList<String> mCoursesItems;
    // TURMAS - DEPENDE DO CURSO
    private List<Turma> mListTeams = new ArrayList<>();
    private ArrayList<String> mTeamsItems;
    // DISCIPLINAS
    private List<Disciplina> mListDisciplines = new ArrayList<>();
    private ArrayList<String> mDisciplinesItems;
    // ADAPTER PARA INSERCAO DE DADOS NOS SPINNERS
    private ArrayAdapter<String> mSpinnersArrayAdapter;
    // INSTANCIA DAS API'S - RETROFIT PARA PEGAR E ENVIAR DADOS, PROFESSOR, FORNECE OS METODOS PARA ENVIO E RECEBIMENTO.
    private Retrofit mRetrofit;
    private TeacherAPI mTeacherAPI;
    // RELATIVE LAYOUT'S UTILIZADOS PARA MOSTRAR SPINNER DE CURSOS, SE ESCOLA TEM CURESO, E MOSTRAR TURMAS, SE CURSO TEM TURMA
    private RelativeLayout mRLCourses;
    private RelativeLayout mRLTeams;

    // BUTTON
    private Button mBtnSetNewTeach;

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		/*
		 * 1 -> Layout a ser utilizado como fragment
		 * 2 -> ViewGroup, pode ser o ViewGroup pai ou pode ser null(caso nao queira ou desnecessario utilizar).
		 * */
		View view = inflater.inflate(R.layout.teacher_fragment_teach_set_new, null);

        // Instanciando os Spinner com seus valores padroes.
        // Spinner de Escolas
        mSPSchools = (Spinner) view.findViewById(R.id.tf_teach_setnew_sp_schools);
        mSchoolsItems = new ArrayList<>();
        mSchoolsItems.add("Selecione a Escola...");
        mSpinnersArrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, mSchoolsItems);
        mSPSchools.setAdapter(mSpinnersArrayAdapter);

        // Spinner de Cursos.
        mSPCourses = (Spinner) view.findViewById(R.id.tf_teach_setnew_sp_courses);
        mCoursesItems = new ArrayList<>();
        mCoursesItems.add("Selecione o Curso...");
        mSpinnersArrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, mCoursesItems);
        mSPCourses.setAdapter(mSpinnersArrayAdapter);

        // Spinner de Disciplinas.
        mSPTeams = (Spinner) view.findViewById(R.id.tf_teach_setnew_sp_teams);
        mTeamsItems = new ArrayList<>();
        mTeamsItems.add("Selecione a Turma...");
        mSpinnersArrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, mTeamsItems);
        mSPTeams.setAdapter(mSpinnersArrayAdapter);

        // Spinner de Disciplinas.
        mSPDisciplines = (Spinner) view.findViewById(R.id.tf_teach_setnew_sp_disciplines);
        mDisciplinesItems = new ArrayList<>();
        mDisciplinesItems.add("Selecione a Disciplina...");
        mSpinnersArrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, mDisciplinesItems);
        mSPDisciplines.setAdapter(mSpinnersArrayAdapter);

        // Relative Layout onde fica o spinner de cursos, altura == 0dp, so aparecera se escola tiver cursos, altura = wrap_content, marginTop = 10
        mRLCourses = (RelativeLayout) view.findViewById(R.id.tf_teach_setnew_rl_courses);
        // Relative Layout onde fica o spinner de turmas, altura == 0dp, so aparecera se curso tiver turmas, altura = wrap_content, marginTop = 10
        mRLTeams = (RelativeLayout) view.findViewById(R.id.tf_teach_setnew_rl_teams);

        // Criando e instanciando objeto Retrofit e TeacherAPI para chamadas e envios de dados.
        mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())  // ConverterFactory para transformar os dados vindos em formato JSONString em POJO's java.
                .baseUrl(AppConstants.SERVER_URL.toString()) // Url base a de onde os dados estao, em noso caso url base do servidor.
                .build();
        mTeacherAPI = mRetrofit.create(TeacherAPI.class); // Pegando nosso TeacherAPI para utilizar-mos para as chamadas.

        //Metodo que inseri as escolas vindas do DB no Spinner Schools
        getSchools(1);
        Log.i("WEB", "TeacherFragmentTeachSetNew - Chamando getSchools(0)");
        getDisciplines(1);
        Log.i("WEB", "TeacherFragmentTeachSetNew - Chamando getDisciplines(0)");

        //Implementacao do Listener do Spinner mySchools
        mSPSchools.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0) {
                    String item = mSPSchools.getItemAtPosition(position).toString();
                    int schoolId = mListSchools.get(position).getId();
                    if (!item.equals(mSPSchools.getItemAtPosition(0))) {
                        GenericMethods.showMessage(getActivity(), "Escola: " + item);
                    } else {
                        mRLCourses.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 34));
                        getCourses(schoolId);
                    }
                }else{
                    getSchools(1);
                    getDisciplines(1);
                    //Lembrar de remover esses ifs e else de fora. apenas testes iniciais. // lembrar de recomeçar o login
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Metodo nao faz nada.
            }
        });

        //Implementacao do Listener do Spinner myCourses
        mSPCourses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0){
                    String item = mSPCourses.getItemAtPosition(position).toString();
                    int itemId = mListCourses.get(position).getId();
                    int schoolId = mListCourses.get(position).getEscola();
                    if(!item.equals(mSPCourses.getItemAtPosition(0))){
                        GenericMethods.showMessage(getActivity(), "Curso: " + item);
                    }else{
                        mRLTeams.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 34));
                        getTeams(itemId, schoolId);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Metodo nao faz nada.
            }
        });

        //Implementacao do Listener do Spinner myTeams
        mSPTeams.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0) {
                    String item = mSPTeams.getItemAtPosition(position).toString();
                    if (!item.equals(mSPTeams.getItemAtPosition(0))) {
                        GenericMethods.showMessage(getActivity(), "Turma: " + item);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Metodo nao faz nada.
            }
        });

        //Implementacao do Listener do Spinner myDisciplines
        mSPDisciplines.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0) {
                    String item = mSPDisciplines.getItemAtPosition(position).toString();
                    if (!item.equals(mSPDisciplines.getItemAtPosition(0))) {
                        GenericMethods.showMessage(getActivity(), "Disciplina: " + item);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Metodo nao faz nada.
            }
        });

        //Metodo que inseri as disciplinas vindas do DB no Spinner Disciplines, dependendo da escola selecionada.
        //getDisciplines(int schoolsSelectedId);

		return(view);
	}

    /**
     * Metodo - Pega os dados (A lista de escolas de determinado professor)
     * @param - teacherId - id do Professor
     * @return
     */
    public void getSchools(int teacherId) {
        Call<List<Escola>> call = mTeacherAPI.getSchools(teacherId);

        call.enqueue(new Callback<List<Escola>>() {
            @Override
            public void onResponse(Call<List<Escola>> call, retrofit2.Response<List<Escola>> response) {
                if(response.body() != null){
                    List<Escola> localList = response.body();
                    for(Escola escola: localList){
                        mListSchools.add(escola);
                        mSchoolsItems.add(escola.getNome());
                        Log.i("TFragTeachSetNew", "GetSchools -> Id: " + escola.getId() +", Nome: "+escola.getNome());
                    }
                    mSpinnersArrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, mSchoolsItems);
                    mSPSchools.setAdapter(mSpinnersArrayAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Escola>> call, Throwable t) {
                Log.i("TFragTeachSetNew", "GetSchools -> Error: " + t.getMessage());
            }
        });
    }

    /**
     * Metodo - Pega os dados (A lista de escolas de determinado professor)
     * @param - schoolId - id da Escola
     * @return
     */
    public void getCourses(int schoolId) {
        Call<List<Curso>> call = mTeacherAPI.getCourses(schoolId);

        call.enqueue(new Callback<List<Curso>>() {
            @Override
            public void onResponse(Call<List<Curso>> call, retrofit2.Response<List<Curso>> response) {
                if(response.body() != null){
                    List<Curso> localList = response.body();
                    for(Curso curso: localList){
                        mListCourses.add(curso);
                        mCoursesItems.add(curso.getDescricao());
                    }
                    mSpinnersArrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, mCoursesItems);
                    mSPCourses.setAdapter(mSpinnersArrayAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Curso>> call, Throwable t) {
                Log.i("TFragTeachSetNew", "GetCourses -> Error: " + t.getMessage());
            }
        });
    }

    /**
     * Metodo - Pega os dados (A lista de escolas de determinado professor)
     * @param - courseId - id da Curso
     * @return
     */
    public void getTeams(int courseId, int schoolId) {
        Call<List<Turma>> call = mTeacherAPI.getTeams(courseId, schoolId);

        call.enqueue(new Callback<List<Turma>>() {
            @Override
            public void onResponse(Call<List<Turma>> call, retrofit2.Response<List<Turma>> response) {
                if(response.body() != null){
                    List<Turma> localList = response.body();
                    for(Turma turma: localList){
                        mListTeams.add(turma);
                        mTeamsItems.add("Turma "+turma.getCodigo());
                    }
                    mSpinnersArrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, mCoursesItems);
                    mSPCourses.setAdapter(mSpinnersArrayAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Turma>> call, Throwable t) {
                Log.i("TFragTeachSetNew", "GetCourses -> Error: " + t.getMessage());
            }
        });
    }

    /**
     * Metodo - Pega os dados (A lista de escolas de determinado professor)
     * @param - teacherId - id do Professor
     * @param - schoolId - id da Escola
     * @param - courseId - id do Curso
     * @return
     */
    public void getDisciplines(int teacherId) {
        Call<List<Disciplina>> call = mTeacherAPI.getDisciplines(teacherId);

        call.enqueue(new Callback<List<Disciplina>>() {
            @Override
            public void onResponse(Call<List<Disciplina>> call, retrofit2.Response<List<Disciplina>> response) {
                if(response.body() != null){
                    List<Disciplina> localList = response.body();
                    for(Disciplina disciplina: localList){
                        mListDisciplines.add(disciplina);
                        mDisciplinesItems.add(disciplina.getNome());
                    }
                    mSpinnersArrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, mDisciplinesItems);
                    mSPDisciplines.setAdapter(mSpinnersArrayAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Disciplina>> call, Throwable t) {
                Log.i("TFragTeachSetNew", "GetDisciplines -> Error: " + t.getMessage());
            }
        });
    }



}