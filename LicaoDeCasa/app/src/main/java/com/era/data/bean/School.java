package com.era.data.bean;

//Tabela Escola
public class School {

	private long cId;			//id
	private String cName;		//nome
	private String cAddress;	//endereco
	private String cPhone;		//telefone

	public School() {
		//Construtor Vazio
	}

	public School(long cId, String cName, String cAddress, String cPhone) {
		this.cId = cId;
		this.cName = cName;
		this.cAddress = cAddress;
		this.cPhone = cPhone;
	}

	public long getcId() {
		return cId;
	}

	public void setcId(long cId) {
		this.cId = cId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcAddress() {
		return cAddress;
	}

	public void setcAddress(String cAddress) {
		this.cAddress = cAddress;
	}

	public String getcPhone() {
		return cPhone;
	}

	public void setcPhone(String cPhone) {
		this.cPhone = cPhone;
	}
}
