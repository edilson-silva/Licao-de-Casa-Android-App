package com.era.licaodecasa.model.pojo;

/**
 * Created by Edilson on 19/03/2016.
 */
public class Curso {

    private int id;
    private String descricao;
    private int escola;

    public Curso(){
        //Somente para instancias.
    }

    //Apenas para instancias especiais, criacao e insercao do primeiro item para a lista, item padrao.
    public Curso(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getEscola() { return escola; }

    public void setEscola(int escola) { this.escola = escola; }
}
