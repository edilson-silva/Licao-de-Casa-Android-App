package com.era.licaodecasa.model.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.era.licaodecasa.model.pojo.Aluno;
import com.era.licaodecasa.R;

import java.util.List;

/**
 * Created by Edilson on 02/03/2016.
 */
public class ListAlunosAdapter extends RecyclerView.Adapter<ListAlunosAdapter.MyViewHolder>{

    private List<Aluno> mListAlunos;
    private LayoutInflater mLayoutInflater;

    //Construtor
    public ListAlunosAdapter(Context c, List<Aluno> alunos){
        mListAlunos = alunos;
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
        myViewHolder.tvStudentRegistration.setText(""+mListAlunos.get(position).getId());
        myViewHolder.tvStudentData.setText(mListAlunos.get(position).getDados());
        myViewHolder.tvStudentName.setText(mListAlunos.get(position).getNome());
        myViewHolder.tvStudentEmail.setText(mListAlunos.get(position).getEmail());
        myViewHolder.tvStudentAverage.setText(""+mListAlunos.get(position).getMedia());
    }

    // Tamanho da nossa lista de Students(estudantes)
    @Override
    public int getItemCount(){
        return mListAlunos.size();
    }

    // Esta classe interna nos fornecera cada item da lista
    public class MyViewHolder extends RecyclerView.ViewHolder{
        // DADOS DA VIEW, SINGLE STUDENT
        public TextView tvStudentRegistration; // Matricula
        public TextView tvStudentData; // Dados
        public TextView tvStudentName; // Nome
        public TextView tvStudentEmail; // Email
        public TextView tvStudentAverage; // Media

        public MyViewHolder(View itemView){
            // Nao eh necessario fazer referencia ao root(layout) pois ele esta sendo referenciado acima na cricao do adapter
            super(itemView);
            tvStudentRegistration = (TextView) itemView.findViewById(R.id.sstudent_tv_registration);
            tvStudentData = (TextView) itemView.findViewById(R.id.sstudent_tv_data);
            tvStudentName = (TextView) itemView.findViewById(R.id.sstudent_tv_name);
            tvStudentEmail = (TextView) itemView.findViewById(R.id.sstudent_tv_email);
            tvStudentAverage = (TextView) itemView.findViewById(R.id.sstudent_tv_average);
        }

    }
}
