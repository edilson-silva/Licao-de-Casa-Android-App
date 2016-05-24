package com.era.data.bean;

//Tabela Questao
public class Question {

	private long qId;				//id
	private Task qTask;				//id da tarefa
	private int qNumber;			//numero
	private String qDescription;	//descricao
	private int qType;				//tipo
	private int qPoints;				//pontos

	public Question() {
		//Construtor Vazio
	}

	public Question(long qId, Task qTask, int qNumber, String qDescription, int qType, int qPoints) {
		this.qId = qId;
		this.qTask = qTask;
		this.qNumber = qNumber;
		this.qDescription = qDescription;
		this.qType = qType;
		this.qPoints = qPoints;
	}

	public long getqId() {
		return qId;
	}

	public void setqId(long qId) {
		this.qId = qId;
	}

	public Task getqTask() {
		return qTask;
	}

	public void setqTask(Task qTask) {
		this.qTask = qTask;
	}

	public int getqNumber() {
		return qNumber;
	}

	public void setqNumber(int qNumber) {
		this.qNumber = qNumber;
	}

	public String getqDescription() {
		return qDescription;
	}

	public void setqDescription(String qDescription) {
		this.qDescription = qDescription;
	}

	public int getqType() {
		return qType;
	}

	public void setqType(int qType) {
		this.qType = qType;
	}

	public int getqPoints() {
		return qPoints;
	}

	public void setqPoints(int qPoints) {
		this.qPoints = qPoints;
	}
}
