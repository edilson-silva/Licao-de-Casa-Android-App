package com.era.data.bean;

//Tabela Resposta_Item
public class ItemResponse {

	private long rId;			//id
	private Student rStudent;	//id do aluno/estudante
	private Item rItem;			//id do item

	public ItemResponse() {
		//Construtor Vazio
	}

	public ItemResponse(long rId, Student rStudent, Item rItem) {
		this.rId = rId;
		this.rStudent = rStudent;
		this.rItem = rItem;
	}

	public long getrId() {
		return rId;
	}

	public void setrId(long rId) {
		this.rId = rId;
	}

	public Student getrStudent() {
		return rStudent;
	}

	public void setrStudent(Student rStudent) {
		this.rStudent = rStudent;
	}

	public Item getrItem() {
		return rItem;
	}

	public void setrItem(Item rItem) {
		this.rItem = rItem;
	}
}
