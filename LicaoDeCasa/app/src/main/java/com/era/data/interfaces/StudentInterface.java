package com.era.data.interfaces;

//import com.era.tablesdb.AutomaticLoginUser;

import android.content.Context;

public interface StudentInterface {

	// OPERACOES EXTERNAS(SERVIDOR WEB);

	/**
	 * @method selectExternalAllStudents - Seleciona todos os alunos do Sistema.
	 * @param
	 * @return users
	 */
	public void selectExternalAllStudents(String urlComplement, Context activityContext, String flagMethodIndicator, boolean showProgressDialog, String dialogMessage);

}