package com.era.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.era.data.bean.Student;
import com.era.data.connection.webserver.CustomJsonArrayRequest;
import com.era.data.connection.webserver.CustomJsonObjectRequest;
import com.era.data.dao.TeacherDAO;
import com.era.extras.AppConstants;
import com.era.licaodecasa.R;
import com.era.licaodecasa.api.TeacherAPI;
import com.era.licaodecasa.model.adapters.ListAlunosAdapter;
import com.era.licaodecasa.model.pojo.Aluno;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TeacherFragmentListStudents extends Fragment {

    private RecyclerView mRecyclerView;
    private List<Student> mListStudents; // VOLTAR - SE FOR UTILIZA VOLLEY OU ASYNCTASK -> OKHTTP
    private List<Aluno> mListAlunos;
    private TeacherDAO mTeacherDAO; // VOLTAR - SE FOR UTILIZA VOLLEY OU ASYNCTASK -> OKHTTP

    private CustomJsonObjectRequest customJsonObjectRequest; // VOLTAR - SE FOR UTILIZA VOLLEY
    private CustomJsonArrayRequest customJsonArrayRequest; // VOLTAR - SE FOR UTILIZA VOLLEY

    ProgressDialog mProgressDialog;

    //VOLLEY
    private RequestQueue rq; // VOLTAR - SE FOR UTILIZA VOLLEY
    private Map<String, String> mParams; // VOLTAR - SE FOR UTILIZA VOLLEY

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.teacher_fragment_list_students, container, false);

        // INICIALIZANDO O REQUESTQUEUE
        rq = Volley.newRequestQueue(getActivity()); // VOLTAR - SE FOR UTILIZA VOLLEY

        // INICIANDO O PROGRESS DIALOG
        mProgressDialog = new ProgressDialog(getActivity()); // UTILIZADO PARA EXIBIR LOADING

        // RECYCLERVIEW
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_my_list);
        Log.i("RECYCLERVIEW", "VIEW ATRIBUIDA A VARIAVEL");

        // Informa que o tamanho do RecyclerView nao vai mudar, questao de otimizacao.
        mRecyclerView.setHasFixedSize(true);
        Log.i("RECYCLERVIEW", "SETADO FIXEDSIZE");

        // Implementado para carregar mais itens, adicionando-os ao final da lista.
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                Log.i("RECYCLERVIEW", "CLICK LISTENER - OnScrollStateChanged()");
                super.onScrollStateChanged(recyclerView, newState);
            }

            // METODO DE SCROLL, PARA CATTEGAR MAIS DADOS
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.i("RECYCLERVIEW", "CLICK LISTENER - onScrolled()");
                // Pegando o Layout do RecyclerView
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                // Pegando o Adapter do RecyclerView
                ListAlunosAdapter listAlunosAdapter = (ListAlunosAdapter) mRecyclerView.getAdapter();

                // Verificando se o tamanho da lista eh igual ao tamanho da ultima view + 1 (indice inicia com 0).
                // Se true, quer dizer que chegamos ao ultimo item, e queremos carregar(adicionar) mais itens.
                //if (mListAlunos.size() == linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1) { // VOLLEY OU ASYNCTASK - OKHTTP
                if (mListAlunos.size() == linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1) {
                    // Criando lista que guardara nossa lista temporariamente para passar a mesma e add o item a ela.
                    //List<Student> listAuxiliar = ((TeacherActivity) getActivity().get);

                }
            }

        });

        // Inicializando as variaveis mStudentDAO, mTeacherDAO para nao dar erro de NullPointerException
        mTeacherDAO = new TeacherDAO(getActivity());

        // LINEAR LAYOUT MANAGER
        // LinearLayoutManager espera sempre com um contexto, o contexto do fragment nao serve aqui.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        // Indica que o Layout sera vertical e o scroll aparecera vertical
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // RecycleView recebe como LayoutManager, o nosso layout personalizado(linearLayoutManager)
        mRecyclerView.setLayoutManager(linearLayoutManager);

        // CHAMA O METODO QUE PREENCHE O RECYCLERVIEW
        //setStudentListAdapter();
        setDataRecyclerView();

        return view;
    }

    /**
     * @Method - Cria a lista de Alunos a ser exibida.
     */
    public void setDataRecyclerView(){
        mListAlunos = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())  // ConverterFactory para transformar os dados vindos em formato JSONString em POJO's java.
                .baseUrl(AppConstants.SERVER_URL.toString()) // Url base a de onde os dados estao, em noso caso url base do servidor.
                .build();

        TeacherAPI teacherAPI = retrofit.create(TeacherAPI.class); // Pegando nosso TeacherAPI para utilizar-mos para as chamadas.

        Call<List<Aluno>> call = teacherAPI.getMyStudents(0);

        call.enqueue(new Callback<List<Aluno>>() {
            @Override
            public void onResponse(Call<List<Aluno>> call, retrofit2.Response<List<Aluno>> response) {
                if(response.body() != null){
                    ListAlunosAdapter listAlunosAdapter = new ListAlunosAdapter(getActivity(), response.body());
                    mRecyclerView.setAdapter(listAlunosAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Aluno>> call, Throwable t) {
                Log.i("TFragListStudent", "Error: "+t.getMessage());
            }
        });
    }

}