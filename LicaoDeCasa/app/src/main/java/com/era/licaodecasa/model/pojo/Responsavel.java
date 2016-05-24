package com.era.licaodecasa.model.pojo;

import android.os.Parcel;

import com.era.data.bean.User;

/**
 * Created by Edilson on 06/03/2016.
 */

//Tabela Responsavel
public class Responsavel extends User {

    private long rId;   //id do Responsavel

    public Responsavel() {
        //Construtor Vazio
    }

    public Responsavel(long uId, String uName, String uPhone, String uEmail, int uType, int uState, int uAutoLoginCode, long rId) {
        super(uId, uName, uPhone, uEmail, uType, uState, uAutoLoginCode);
        this.rId = rId;
    }

    public long getrId() {
        return rId;
    }

    public void setrId(long rId) {
        this.rId = rId;
    }

    // PARCELABLE - IMPLEMENTACAO

    // Passando os dados do Objeto Parcel para o Objeto Student
    public Responsavel(Parcel in) {
        //Construtor vazio somente sobrecarregado com todos os atributos do usuario;
        this.rId = in.readLong();
        this.setuId(in.readLong());
        this.setuName(in.readString());
        this.setuEmail(in.readString());
        this.setuType(in.readInt());
        this.setuState(in.readInt());
        this.setuAutoLoginCode(0);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // Escrevando os dados no Objeto Parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(rId);
        dest.writeLong(this.getuId());
        dest.writeString(this.getuName());
        dest.writeString(this.getuEmail());
        dest.writeInt(this.getuType());
        dest.writeInt(this.getuState());
        dest.writeInt(this.getuAutoLoginCode());
    }

    // Responsavel por fazer-nos pegar os dados novamente (Essa a instancia de uma Classe Parcelable).
    public static final Creator<com.era.data.bean.Responsible> CREATOR = new Creator<com.era.data.bean.Responsible>(){

        @Override
        public com.era.data.bean.Responsible createFromParcel(Parcel source) {
            return new com.era.data.bean.Responsible(source);
        }

        @Override
        public com.era.data.bean.Responsible[] newArray(int size) { return new com.era.data.bean.Responsible[size]; }
    };

}
