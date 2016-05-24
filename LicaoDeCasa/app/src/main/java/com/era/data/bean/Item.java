package com.era.data.bean;

//Tabela Item
public class Item {

	private long iId;				//id
	private Question iQuestion;		//id da questao
	private char letter;			//letra
	private String iDescription;	//descricao
	private int iAnswer;			//resposta

	public Item() {
		//Construtor Vazio
	}

	public Item(long iId, Question iQuestion, char letter, String iDescription, int iAnswer) {
		this.iId = iId;
		this.iQuestion = iQuestion;
		this.letter = letter;
		this.iDescription = iDescription;
		this.iAnswer = iAnswer;
	}

	public long getiId() {
		return iId;
	}

	public void setiId(long iId) {
		this.iId = iId;
	}

	public Question getiQuestion() {
		return iQuestion;
	}

	public void setiQuestion(Question iQuestion) {
		this.iQuestion = iQuestion;
	}

	public char getLetter() {
		return letter;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}

	public String getiDescription() {
		return iDescription;
	}

	public void setiDescription(String iDescription) {
		this.iDescription = iDescription;
	}

	public int getiAnswer() {
		return iAnswer;
	}

	public void setiAnswer(int iAnswer) {
		this.iAnswer = iAnswer;
	}
}
