package com.era.licaodecasa.model.pojo;

/**
 * Created by Edilson on 19/03/2016.
 */
public class Turma {

    private int id;
    private char codigo;
    private int escola;
    private int curso;

    public Turma(){
        //Somente para instancias.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getCodigo() {
        return codigo;
    }

    public void setCodigo(char codigo) {
        this.codigo = codigo;
    }

    public int getEscola() { return escola; }

    public void setEscola(int escola) { this.escola = escola; }

    public int getCurso() { return curso; }

    public void setCurso(int curso) { this.curso = curso; }
}
