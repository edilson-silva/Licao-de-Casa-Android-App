package com.era.data.bean;

//Tabela Tarefa
public class Task {

	private long tId;					//id da tarefa
	private Teacher tTeacher;			//id do professor
	private Class tClass;				//id da turma
	private Discipline tDdiscipline;	//id da disciplina
	private int situation;				//situacao
	private String creationDate;		//data de criacao
	private String closingDta;			//data de enceerramento
	private String extra;				//extra

	public Task() {
		//Construtor Vazio
	}

	public Task(long tId, Teacher tTeacher, Class tClass, Discipline tDdiscipline, int situation, String creationDate, String closingDta, String extra) {
		this.tId = tId;
		this.tTeacher = tTeacher;
		this.tClass = tClass;
		this.tDdiscipline = tDdiscipline;
		this.situation = situation;
		this.creationDate = creationDate;
		this.closingDta = closingDta;
		this.extra = extra;
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

	public Discipline gettDdiscipline() {
		return tDdiscipline;
	}

	public void settDdiscipline(Discipline tDdiscipline) {
		this.tDdiscipline = tDdiscipline;
	}

	public int getSituation() {
		return situation;
	}

	public void setSituation(int situation) {
		this.situation = situation;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getClosingDta() {
		return closingDta;
	}

	public void setClosingDta(String closingDta) {
		this.closingDta = closingDta;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}
}
