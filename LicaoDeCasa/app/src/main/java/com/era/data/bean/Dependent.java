package com.era.data.bean;

/**
 * Created by Edilson on 06/03/2016.
 */

//Tabela Dependente
public class Dependent {

    public long dInt;                   //id do(a) Dependente
    private Responsible dResponsible;   //id do(a) Responsavel
    private Student dStudent;           //id do(a) Aluno/Estudante

    public Dependent() {
        //Construtor Vazio
    }

    public Dependent(long dInt, Responsible dResponsible, Student dStudent) {
        this.dInt = dInt;
        this.dResponsible = dResponsible;
        this.dStudent = dStudent;
    }

    public long getdInt() {
        return dInt;
    }

    public void setdInt(long dInt) {
        this.dInt = dInt;
    }

    public Responsible getdResponsible() {
        return dResponsible;
    }

    public void setdResponsible(Responsible dResponsible) {
        this.dResponsible = dResponsible;
    }

    public Student getdStudent() {
        return dStudent;
    }

    public void setdStudent(Student dStudent) {
        this.dStudent = dStudent;
    }
}
