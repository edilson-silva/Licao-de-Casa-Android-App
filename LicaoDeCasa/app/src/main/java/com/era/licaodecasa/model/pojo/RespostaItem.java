package com.era.licaodecasa.model.pojo;

import com.era.data.bean.*;
import com.era.data.bean.Item;

//Tabela Resposta_Item
public class RespostaItem {

	private long rId;			//id
	private Student rStudent;	//id do aluno/estudante
	private com.era.data.bean.Item rItem;			//id do item

	public RespostaItem() {
		//Construtor Vazio
	}

	public RespostaItem(long rId, Student rStudent, com.era.data.bean.Item rItem) {
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

	public com.era.data.bean.Item getrItem() {
		return rItem;
	}

	public void setrItem(Item rItem) {
		this.rItem = rItem;
	}
}
