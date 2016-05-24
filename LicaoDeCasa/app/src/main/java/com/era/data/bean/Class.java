package com.era.data.bean;

//Tabela Turma/Classe
public class Class {

    private long cId;           // id da Turma do BD
    private School cSchool;     // Objeto cSchool para pegar os dados da Escola
    private Course cCourse;     // Objeto cCourse para pegar os dados do Curso
    private char cCode;         // codigo

    public Class() {
        // Construtor vazio somente para instancias padroes da Turma;
    }

    public Class(long cId, School cSchool, Course cCourse, char cCode) {
        this.cId = cId;
        this.cSchool = cSchool;
        this.cCourse = cCourse;
        this.cCode = cCode;
    }

    public long getcId() {
        return cId;
    }

    public void setcId(long cId) {
        this.cId = cId;
    }

    public School getcSchool() {
        return cSchool;
    }

    public void setcSchool(School cSchool) {
        this.cSchool = cSchool;
    }

    public Course getcCourse() {
        return cCourse;
    }

    public void setcCourse(Course cCourse) {
        this.cCourse = cCourse;
    }

    public char getcCode() {
        return cCode;
    }

    public void setcCode(char cCode) {
        this.cCode = cCode;
    }
}
