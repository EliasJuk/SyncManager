package com.sm.models.empresa;

public class BU {
	private int id;
	private String nome;

	// Construtor padrão
	public BU() {}

 	// Construtor com parâmetros
	public BU(String nome) {
		this.nome = nome;
	}

  // Construtor com parâmetros id/nome
	public BU(int id, String nome) {
		this.nome = nome;
    this.id = id;
	}

	// Getter & Setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

  @Override
  public String toString() {
    return this.nome;
  }
}