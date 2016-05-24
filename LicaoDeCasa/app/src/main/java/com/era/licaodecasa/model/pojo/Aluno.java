package com.era.licaodecasa.model.pojo;

//Tabela Aluno/Estudante
public class Aluno {

	//Objeto user, com todos os atributos em comum
	private Integer id;
	private String dados;
	private String nome;
    private String email;
	private double media;

	public Aluno() {
		// Construtor vazio somente para instancias padroes do usuario;
	}

    public Aluno(Integer id, String dados, String nome, String email, double media) {
		this.id = id;
		this.dados = dados;
		this.nome =	nome;
		this.email = email;
		this. media = media;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDados() {
		return dados;
	}

	public void setDados(String dados) {
		this.dados = dados;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
	}

}