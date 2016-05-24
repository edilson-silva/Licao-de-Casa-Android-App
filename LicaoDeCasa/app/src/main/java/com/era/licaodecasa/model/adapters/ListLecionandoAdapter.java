package com.era.licaodecasa.model.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.era.licaodecasa.R;
import com.era.licaodecasa.model.pojo.Curso;
import com.era.licaodecasa.model.pojo.Disciplina;
import com.era.licaodecasa.model.pojo.Escola;
import com.era.licaodecasa.model.pojo.Turma;

import java.util.List;

/**
 * Created by Edilson on 02/03/2016.
 */
public class ListLecionandoAdapter extends RecyclerView.Adapter<ListLecionandoAdapter.MyViewHolder>{

    private List<Escola> mListEscolas;
    private List<Curso> mListCursos;
    private List<Turma> mListTurmas;
    private List<Disciplina> mListDisciplinas;

    private LayoutInflater mLayoutInflater;

    //Construtor
    public ListLecionandoAdapter(Context c, List<Escola> escolas, List<Curso> cursos, List<Turma> turmas, List<Disciplina> disciplinas){
        mListEscolas = escolas;
        mListCursos = cursos;
        mListTurmas = turmas;
        mListDisciplinas = disciplinas;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // So eh chamado quando tem-se a necessidade de criar uma nova view, ou quando algum das view foi para o estado dirt(Nao pode mais ser utilizado)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Eh preciso criar e inflar a view auqi, pois esse metodo cria e infla(inicia) a view.
        Log.i("LIST STUDENT ADAPTER", "CRIANDO VIEW - onCreateViewHolder");
        View v = mLayoutInflater.inflate(R.layout.single_teaching_line, parent, false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    // Eh chamado toda hora, ele vincula os dados da nossa lista a view.
    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position){
        Log.i("LIST STUDENT ADAPTER", "ADICIONANDO ITENS - onBindViewHolder");
        myViewHolder.tvTeachingSchool.setText(""+mListEscolas.get(position).getId());
        myViewHolder.tvTeachingCourse.setText(mListCursos.get(position).getDescricao());
        myViewHolder.tvTeachingClass.setText(mListTurmas.get(position).getCodigo());
        myViewHolder.tvTeachingDiscipline.setText(mListDisciplinas.get(position).getNome());
    }

    // Tamanho da nossa lista de Students(estudantes)
    @Override
    public int getItemCount(){
        return mListEscolas.size();
    }

    // Esta classe interna nos fornecera cada item da lista
    public class MyViewHolder extends RecyclerView.ViewHolder{
        // DADOS DA VIEW, SINGLE STUDENT
        public TextView tvTeachingSchool;   // Escola
        public TextView tvTeachingCourse;   // Curso
        public TextView tvTeachingClass; // Turma
        public TextView tvTeachingDiscipline;     // Email

        public MyViewHolder(View itemView){
            // Nao eh necessario fazer referencia ao root(layout) pois ele esta sendo referenciado acima na cricao do adapter
            super(itemView);
            tvTeachingSchool = (TextView) itemView.findViewById(R.id.steaching_tv_school); // st_tv_school_text
            tvTeachingCourse = (TextView) itemView.findViewById(R.id.steaching_tv_course);
            tvTeachingClass = (TextView) itemView.findViewById(R.id.steaching_tv_class);
            tvTeachingDiscipline = (TextView) itemView.findViewById(R.id.steaching_tv_discipline);
        }

    }
}
