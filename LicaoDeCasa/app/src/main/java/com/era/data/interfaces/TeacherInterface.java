package com.era.data.interfaces;

import android.content.Context;

/**
 * Created by Edilson on 05/03/2016.
 */
public interface TeacherInterface {
    // OPERACOES EXTERNAS(SERVIDOR WEB);

    /**
     * @method selectExternalAllStudents - Seleciona todos os alunos do Sistema.
     * @param
     * @return users
     */
    public void getMyStudents(String urlComplement, Context activityContext, String flagMethodIndicator, boolean showProgressDialog, String dialogMessage);
}
