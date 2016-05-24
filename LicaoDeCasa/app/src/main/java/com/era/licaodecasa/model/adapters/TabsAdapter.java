package com.era.licaodecasa.model.adapters;

/**
 * Created by Edilson on 28/02/2016.
 */

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.era.fragments.TeacherFragmentTaskCreate;
import com.era.fragments.TeacherFragmentListCurrentTask;
import com.era.fragments.TeacherFragmentListCurrentTeach;
import com.era.fragments.TeacherFragmentTeachSetNew;
import com.era.fragments.TeacherFragmentListStudents;
import com.era.licaodecasa.R;

/**
* Este adaptador fara a ligacao entre o Fragment(Conteudo) que sera apresentado no ViewPager com a Transicao(Slide).
*/


public class TabsAdapter extends FragmentStatePagerAdapter {

    private Context mContext;
    private String[] mTabsTitles;

    public TabsAdapter(FragmentManager fm, Context c) {
        super(fm);
        mContext = c;
        mTabsTitles = c.getResources().getStringArray(R.array.Teacher_TabsValues);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;

        /* ITENS POSITION
        * 0 - TAREFAS ATUAIS, 1 - NOVA TAREFA, 2 - ALUNOS, 3 - DISCIPLINAS ATUAIS, 4 - NOVA DISCIPLINA
        * */

        if(position == 0){
            // FRAGMENT TARRFAS - ATUAIS
            frag = new TeacherFragmentListCurrentTask();
        }else if(position == 1){
            // FRAGMENT TARRFAS - CRIAR
            frag = new TeacherFragmentTaskCreate();
        }else if(position == 2){
            // FRAGMENT LISTAR ESTUDANTES
            frag = new TeacherFragmentListStudents();
        }else if(position == 3){
            // FRAGMENT LECIONANDO - ATUAL
            frag = new TeacherFragmentListCurrentTeach();
        }else if(position == 4){
            // FRAGMENT LECIONANDO - DEFINIR NOVA
            frag = new TeacherFragmentTeachSetNew();
        }
        return frag;
    }

    @Override
    public int getCount() {
        if(mTabsTitles != null){
            return mTabsTitles.length;
        }
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return(mTabsTitles[position]);
    }
}
