package com.era.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.era.licaodecasa.R;

public class TeacherFragmentListCurrentTask extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		/*
		 * 1 -> Layout a ser utilizado como fragment
		 * 2 -> ViewGroup, pode ser o ViewGroup pai ou pode ser null(caso nao queira ou desnecessario utilizar).
		 * */
		View view = inflater.inflate(R.layout.teacher_fragment_list_current_tasks, null);


		return(view);
	}

}
