package com.era.licaodecasa.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

//Tabela Usuario
public class Usuario implements Parcelable{

    //Dados.
    private int id;   		            // id
    private String nome;		        // nome
    private String telefone;            // telefone
    private String email;		        // Email
    //private String senha;             // Senha - Nao utilizado aqui.
    private int tipo;		            // tipo
    private int estado;			        // estado
    //private String data_cadastro;     // Senha - Nao utilizado aqui.

    public Usuario() {
        //Construtor vazio somente para instancias padroes do usuario;
    }

    public Usuario(int id, String nome, String telefone, String email, int tipo, int estado) {
        //Construtor vazio somente sobrecarregado com todos os atributos do usuario;
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.tipo = tipo;
        this.estado = estado;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    // PARCELABLE - IMPLEMENTACAO

    // Passando os dados do Objeto Parcel para o Objeto User
    public Usuario(Parcel in) {
        //Construtor vazio somente sobrecarregado com todos os atributos do usuario;
        this.id = in.readInt();
        this.nome = in.readString();
        this.telefone = in.readString();
        this.email = in.readString();
        this.tipo = in.readInt();
        this.estado = in.readInt();
    }

    // METODOS IMPLEMENTADOS PELO PARCELABLE, PARA FAZER A ESCRITA E LEIRUA DO OBJETO ENTRE AS ACTIVITYS.

    @Override
    public int describeContents() {
        return 0;
    }

    // Escrevando os dados no Objeto Parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nome);
        dest.writeString(telefone);
        dest.writeString(email);
        dest.writeInt(tipo);
        dest.writeInt(estado);
    }

    // Responsavel por fazer-nos pegar os dados novamente (Essa a instancia de uma Classe Parcelable).
    public static final Creator<Usuario> CREATOR = new Creator<Usuario>(){

        @Override
        public Usuario createFromParcel(Parcel source) { return new Usuario(source); }

        @Override
        public Usuario[] newArray(int size) { return new Usuario[size]; }
    };

}