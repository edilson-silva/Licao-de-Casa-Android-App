package com.era.data.bean;

//Tabela Finalizacao
public class Finalization {

	private long fId;			//id
	private Student fStudent;	//id do Aluno/Estudante
	private Task tTask;			//id da Tarefa

	public Finalization() {
		//Construtor Vazio
	}

	public Finalization(long fId, Student fStudent, Task tTask) {
		this.fId = fId;
		this.fStudent = fStudent;
		this.tTask = tTask;
	}

	public long getfId() {
		return fId;
	}

	public void setfId(long fId) {
		this.fId = fId;
	}

	public Student getfStudent() {
		return fStudent;
	}

	public void setfStudent(Student fStudent) {
		this.fStudent = fStudent;
	}

	public Task gettTask() {
		return tTask;
	}

	public void settTask(Task tTask) {
		this.tTask = tTask;
	}
}
