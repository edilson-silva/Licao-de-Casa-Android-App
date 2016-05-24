package com.era.data.bean;

/**
 * Created by Edilson on 06/03/2016.
 */

// Tabela Planejamento
public class Planning {

    //Dados.
    private int pId;            // id
    private Teacher pTeacher;   // id do Professor

    public Planning() {
        // Contrutor Vazio
    }

    public Planning(int pId, Teacher pTeacher) {
        this.pId = pId;
        this.pTeacher = pTeacher;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public Teacher getpTeacher() {
        return pTeacher;
    }

    public void setpTeacher(Teacher pTeacher) {
        this.pTeacher = pTeacher;
    }
}
