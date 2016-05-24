package com.era.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

//Tabela Usuario
public class User implements Parcelable{

    //Dados.
    private long uId;   		// id
    private String uName;		// nome
    private String uPhone;      // telefone
    private String uEmail;		// Email
    //private String uPassword;   // Senha - Nao utilizado aqui.
    private int uType;			// tipo
    private int uState;			// estado
    private int uAutoLoginCode;	// auto_login, variavel para login interno automatico

    public User() {
        //Construtor vazio somente para instancias padroes do usuario;
    }

    public User(long uId, String uName, String uPhone, String uEmail, int uType, int uState, int uAutoLoginCode) {
        //Construtor vazio somente sobrecarregado com todos os atributos do usuario;
        this.uId = uId;
        this.uName = uName;
        this.uPhone = uPhone;
        this.uEmail = uEmail;
        this.uType = uType;
        this.uState = uState;
        this.uAutoLoginCode = uAutoLoginCode;
    }

    public long getuId() { return uId; }

    public void setuId(long id) { this.uId = id; }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public int getuType() {
        return uType;
    }

    public void setuType(int uType) {
        this.uType = uType;
    }

    public int getuState() {
        return uState;
    }

    public void setuState(int uState) {
        this.uState = uState;
    }

    public int getuAutoLoginCode() {
        return uAutoLoginCode;
    }

    public void setuAutoLoginCode(int uAutoLoginCode) {
        this.uAutoLoginCode = uAutoLoginCode;
    }

    // PARCELABLE - IMPLEMENTACAO

    // Passando os dados do Objeto Parcel para o Objeto User
    public User(Parcel in) {
        //Construtor vazio somente sobrecarregado com todos os atributos do usuario;
        this.uId = in.readLong();
        this.uName = in.readString();
        this.uPhone = in.readString();
        this.uEmail = in.readString();
        this.uType = in.readInt();
        this.uState = in.readInt();
        this.uAutoLoginCode = in.readInt();
    }

    // METODOS IMPLEMENTADOS PELO PARCELABLE, PARA FAZER A ESCRITA E LEIRUA DO OBJETO ENTRE AS ACTIVITYS.

    @Override
    public int describeContents() {
        return 0;
    }

    // Escrevando os dados no Objeto Parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(uId);
        dest.writeString(uName);
        dest.writeString(uPhone);
        dest.writeString(uEmail);
        dest.writeInt(uType);
        dest.writeInt(uState);
        dest.writeInt(uAutoLoginCode);
    }

    // Responsavel por fazer-nos pegar os dados novamente (Essa a instancia de uma Classe Parcelable).
    public static final Creator<User> CREATOR = new Creator<User>(){

        @Override
        public User createFromParcel(Parcel source) { return new User(source); }

        @Override
        public User[] newArray(int size) { return new User[size]; }
    };
}