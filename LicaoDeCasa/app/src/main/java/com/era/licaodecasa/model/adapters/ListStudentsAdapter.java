package com.era.licaodecasa.model.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.era.data.bean.Student;
import com.era.licaodecasa.R;

import java.util.List;

/**
 * Created by Edilson on 02/03/2016.
 */
public class ListStudentsAdapter extends RecyclerView.Adapter<ListStudentsAdapter.MyViewHolder>{

    private List<Student> mListStudent;
    private LayoutInflater mLayoutInflater;

    //Construtor
    public ListStudentsAdapter(Context c, List<Student> studentsBean){
        mListStudent = studentsBean;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // So eh chamado quando tem-se a necessidade de criar uma nova view, ou quando algum das view foi para o estado dirt(Nao pode mais ser utilizado)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Eh preciso criar e inflar a view auqi, pois esse metodo cria e infla(inicia) a view.
        Log.i("LIST STUDENT ADAPTER", "CRIANDO VIEW - onCreateViewHolder");
        View v = mLayoutInflater.inflate(R.layout.single_student_line, parent, false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    // Eh chamado toda hora, ele vincula os dados da nossa lista a view.
    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position){
        Log.i("LIST STUDENT ADAPTER", "ADICIONANDO ITENS - onBindViewHolder");
        /*
        * public TextView tvStudentRegistration; // Matricula
          public TextView tvStudentSchool; // Nome da Instituicao
          public TextView tvStudentAverage; // Media Geral
          public TextView tvStudentSerie; // Serie
          public TextView tvStudentClass; // Turma
          public TextView tvStudentDiscipline; // Disciplina
          public TextView tvStudentName; // Nome
          public TextView tvStudentEmail; // Email
        * */
        //myViewHolder.tvStudentRegistration.setText(""+mListStudent.get(position).getsId());
        //myViewHolder.tvStudentSchool.setText(mListStudent.get(position).getSchool().getuName());
        //myViewHolder.tvStudentAverage.setText(""+mListStudent.get(i).getsId());
        //myViewHolder.tvStudentSerie.setText(mListStudent.get(i).getsClass().setSerie());
        //myViewHolder.tvStudentClass.setText(mListStudent.get(position).getsClass().getCode());
        //myViewHolder.tvStudentDiscipline.setText(""+mListStudent.get(i).getsClass());
        myViewHolder.tvStudentRegistration.setText(""+mListStudent.get(position).getsId());
        myViewHolder.tvStudentData.setText(mListStudent.get(position).getsData());
        myViewHolder.tvStudentName.setText(mListStudent.get(position).getuName());
        myViewHolder.tvStudentEmail.setText(mListStudent.get(position).getuEmail());
        myViewHolder.tvStudentAverage.setText(""+mListStudent.get(position).getsAverage());
    }

    // Tamanho da nossa lista de Students(estudantes)
    @Override
    public int getItemCount(){
        return mListStudent.size();
    }

    // Esta classe interna nos fornecera cada item da lista
    public class MyViewHolder extends RecyclerView.ViewHolder{
        // DADOS DA VIEW, SINGLE STUDENT
        //public TextView tvStudentRegistration; // Matricula
        //public TextView tvStudentSchool; // Nome da Instituicao
        //public TextView tvStudentAverage; // Media Geral
        //public TextView tvStudentSerie; // Serie
        //public TextView tvStudentClass; // Turma
       // public TextView tvStudentDiscipline; // Disciplina
        public TextView tvStudentRegistration; // Matricula
        public TextView tvStudentData; // Dados
        public TextView tvStudentName; // Nome
        public TextView tvStudentEmail; // Email
        public TextView tvStudentAverage; // Media

        public MyViewHolder(View itemView){
            // Nao eh necessario fazer referencia ao root(layout) pois ele esta sendo referenciado acima na cricao do adapter
            super(itemView);

            // Incorporando os componentes de view as variaveis
            //tvStudentRegistration = (TextView) itemView.findViewById(R.id.ss_tv_registration);
            //tvStudentSchool = (TextView) itemView.findViewById(R.id.ss_tv_school);
            //tvStudentAverage = (TextView) itemView.findViewById(R.id.ss_tv_average);
            //tvStudentSerie = (TextView) itemView.findViewById(R.id.ss_tv_serie);
            //tvStudentClass = (TextView) itemView.findViewById(R.id.ss_tv_class);
            //tvStudentDiscipline = (TextView) itemView.findViewById(R.id.ss_tv_discipline);
            tvStudentRegistration = (TextView) itemView.findViewById(R.id.sstudent_tv_registration);
            tvStudentData = (TextView) itemView.findViewById(R.id.sstudent_tv_data);
            tvStudentName = (TextView) itemView.findViewById(R.id.sstudent_tv_name);
            tvStudentEmail = (TextView) itemView.findViewById(R.id.sstudent_tv_email);
            tvStudentAverage = (TextView) itemView.findViewById(R.id.sstudent_tv_average);
        }

    }
}
