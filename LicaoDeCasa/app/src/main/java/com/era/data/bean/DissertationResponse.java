package com.era.data.bean;

//Tabela Resposta_Dissertacao
public class DissertationResponse {

	private long rdId;				//id
	private Student rdStudent;		//id do aluno/estudante
	private Question rdQuestion;	//id da questao
	private String rdText;			//texto
	private int rdSituation;		//situacao

	public DissertationResponse() {
		//Connstrutor Vazio
	}

	public DissertationResponse(long rdId, Student rdStudent, Question rdQuestion, String rdText, int rdSituation) {
		this.rdId = rdId;
		this.rdStudent = rdStudent;
		this.rdQuestion = rdQuestion;
		this.rdText = rdText;
		this.rdSituation = rdSituation;
	}

	public long getRdId() {
		return rdId;
	}

	public void setRdId(long rdId) {
		this.rdId = rdId;
	}

	public Student getRdStudent() {
		return rdStudent;
	}

	public void setRdStudent(Student rdStudent) {
		this.rdStudent = rdStudent;
	}

	public Question getRdQuestion() {
		return rdQuestion;
	}

	public void setRdQuestion(Question rdQuestion) {
		this.rdQuestion = rdQuestion;
	}

	public String getRdText() {
		return rdText;
	}

	public void setRdText(String rdText) {
		this.rdText = rdText;
	}

	public int getRdSituation() {
		return rdSituation;
	}

	public void setRdSituation(int rdSituation) {
		this.rdSituation = rdSituation;
	}
}
