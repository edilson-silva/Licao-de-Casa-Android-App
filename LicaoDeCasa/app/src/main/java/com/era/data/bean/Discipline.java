package com.era.data.bean;

//Tabela Disciplina
public class Discipline {
	
	private long disciplineId;		//id da disciplina
	private String name;			//nome
	private String description;		//descricao
	
	public long getDisciplineId() {
		return disciplineId;
	}
	
	public void setDisciplineId(long disciplineId) {
		this.disciplineId = disciplineId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
