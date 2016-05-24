package com.era.licaodecasa.model.pojo;

/**
 * Created by Edilson on 19/03/2016.
 */
public class Tarefa {

    private int id;
    private int professor;
    private int turma;
    private int disciplina;
    private int situacao;

    public Tarefa(){
        //Somente para instancias.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProfessor() {
        return professor;
    }

    public void setProfessor(int professor) {
        this.professor = professor;
    }

    public int getTurma() {
        return turma;
    }

    public void setTurma(int turma) {
        this.turma = turma;
    }

    public int getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(int disciplina) {
        this.disciplina = disciplina;
    }

    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }
}
