package com.era.licaodecasa.model.pojo;

/**
 * Created by Edilson on 19/03/2016.
 */
public class Escola {
    private int id;
    private String nome;
    private String endereco;
    private String telefone;

    public Escola(){
        //Apenas para instancias.
    }

    //Apenas para instancias especiais, criacao e insercao do primeiro item para a lista, item padrao.
    public Escola(String nome){
        this.nome = nome;
    }

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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
