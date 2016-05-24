package com.era.licaodecasa.model.pojo;

/**
 * Created by Edilson on 19/03/2016.
 */
public class Disciplina {

    private int id;
    private String nome;
    private String descricao;

    public Disciplina(){
        //Apenas para instacias.
    }

    //Apenas para instancias especiais, criacao e insercao do primeiro item para a lista, item padrao.
    public Disciplina(String nome){ this.nome = nome; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
