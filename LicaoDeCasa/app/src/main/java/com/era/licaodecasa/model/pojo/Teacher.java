package com.era.licaodecasa.model.pojo;

import android.os.Parcel;

import com.era.data.bean.User;

//Tabela Professor
public class Teacher extends User {

	//Objeto user, com todos os atributos em comum
	private long tId;       // id do Professor no BD com os outros atributos em comum
	private String tCPF;    // CPF

    public Teacher() {
        // Construtor vazio somente para instancias padroes do usuario;
    }

    public Teacher(long tId, long uId, String uName, String uPhone, String uEmail, int uType, int uState, int uAutoLoginCode, String tCPF) {
        super(uId, uName, uPhone, uEmail, uType, uState, uAutoLoginCode);
        this.tId = tId;
        this.tCPF = tCPF;
    }

	public long gettId() {
		return tId;
	}
	
	public void settId(long tId) {
		this.tId = tId;
	}
	
	public String gettCPF() {
		return tCPF;
	}
	
	public void settCPF(String tCPF) {
		this.tCPF = tCPF;
	}

// PARCELABLE - IMPLEMENTACAO

    // Passando os dados do Objeto Parcel para o Objeto Student
    public Teacher(Parcel in) {
        //Construtor vazio somente sobrecarregado com todos os atributos do usuario;
        this.tId = in.readLong();
        this.setuId(in.readLong());
        this.setuName(in.readString());
        this.setuEmail(in.readString());
        this.setuType(in.readInt());
        this.setuState(in.readInt());
        this.setuAutoLoginCode(0);
        this.settCPF(in.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // Escrevando os dados no Objeto Parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(tId);
        dest.writeLong(this.getuId());
        dest.writeString(this.getuName());
        dest.writeString(this.getuEmail());
        dest.writeInt(this.getuType());
        dest.writeInt(this.getuState());
        dest.writeInt(this.getuAutoLoginCode());
        dest.writeString(this.gettCPF());
    }

    // Responsavel por fazer-nos pegar os dados novamente (Essa � a instancia de uma Classe Parcelable).
    public static final Creator<com.era.data.bean.Teacher> CREATOR = new Creator<com.era.data.bean.Teacher>(){

        @Override
        public com.era.data.bean.Teacher createFromParcel(Parcel source) {
            return new com.era.data.bean.Teacher(source);
        }

        @Override
        public com.era.data.bean.Teacher[] newArray(int size) { return new com.era.data.bean.Teacher[size]; }
    };

}
