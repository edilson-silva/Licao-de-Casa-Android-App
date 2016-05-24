package com.era.data.bean;

import android.os.Parcel;

//Tabela Aluno/Estudante
public class Student extends User {
	
	//Objeto user, com todos os atributos em comum
	private long sId;	        //id
	private Class sClass;       //id da turma/classe
	private String sData;	    //dados
    private double sAverage;	//media

	public Student() {
		// Construtor vazio somente para instancias padroes do usuario;
	}

    public Student(long sId, long uId, String uName, String uPhone, String uEmail, int uType, int uState, int uAutoLoginCode, Class sClass) {
        super(uId, uName, uPhone, uEmail, uType, uState, uAutoLoginCode);
        this.sId = sId;
        this.sClass = sClass;
    }

    public long getsId() {
		return sId;
	}

	public void setsId(long sId) {
		this.sId = sId;
	}

    public Class getsClass() {
        return sClass;
    }

    public void setsClass(Class sClass) {
        this.sClass = sClass;
    }

	public String getsData() {
		return sData;
	}

	public void setsData(String sData) {
		this.sData = sData;
	}

    public double getsAverage() {
        return sAverage;
    }

    public void setsAverage(double sAverage) {
        this.sAverage = sAverage;
    }

    // PARCELABLE - IMPLEMENTACAO

	// Passando os dados do Objeto Parcel para o Objeto Student
	public Student(Parcel in) {
		//Construtor vazio somente sobrecarregado com todos os atributos do usuario;
		this.sId = in.readLong();
		this.setuId(in.readLong());
		this.setuName(in.readString());
		this.setuEmail(in.readString());
		this.setuType(in.readInt());
		this.setuState(in.readInt());
		this.setuAutoLoginCode(0);
		this.sClass.setcId(in.readLong());
	}

	@Override
	public int describeContents() {
		return 0;
	}

	// Escrevando os dados no Objeto Parcel
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(sId);
		dest.writeLong(this.getuId());
		dest.writeString(this.getuName());
		dest.writeString(this.getuEmail());
		dest.writeInt(this.getuType());
		dest.writeInt(this.getuState());
		dest.writeInt(this.getuAutoLoginCode());
		dest.writeLong(this.getsClass().getcId());
	}

	// Responsavel por fazer-nos pegar os dados novamente (Essa a instancia de uma Classe Parcelable).
	public static final Creator<Student> CREATOR = new Creator<Student>(){

		@Override
		public Student createFromParcel(Parcel source) {
			return new Student(source);
		}

		@Override
		public Student[] newArray(int size) { return new Student[size]; }
	};

}