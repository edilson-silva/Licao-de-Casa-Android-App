package com.era.data.bean;

//Tabela Leciona
public class Teaches {
	
	private long tId;					//id
	private Teacher tTeacher;			//Objeto Teacher para pegar o id do Professor
	private Class tClass;				//Objeto Class para pegar o id da Classe
	private Discipline tDiscipline;		//Objeto Discipline para pegar o id da Disciplina

	public Teaches() {
		//Cnstrutor Vazio
	}

	public Teaches(long tId, Teacher tTeacher, Class tClass, Discipline tDiscipline) {
		this.tId = tId;
		this.tTeacher = tTeacher;
		this.tClass = tClass;
		this.tDiscipline = tDiscipline;
	}

	public long gettId() {
		return tId;
	}
	
	public void settId(long tId) {
		this.tId = tId;
	}

	public Teacher gettTeacher() {
		return tTeacher;
	}

	public void settTeacher(Teacher tTeacher) {
		this.tTeacher = tTeacher;
	}

	public Class gettClass() {
		return tClass;
	}

	public void settClass(Class tClass) {
		this.tClass = tClass;
	}

	public Discipline gettDiscipline() {
		return tDiscipline;
	}

	public void settDiscipline(Discipline tDiscipline) {
		this.tDiscipline = tDiscipline;
	}
}

