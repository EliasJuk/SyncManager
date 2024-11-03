package com.sm.models.empresa;

import java.sql.Date;

public class Setor {
	private int id;
	private String nome;
	private int buId;
	private Date createdAt;

	// Construtor padrão
	public Setor() {
	}

	public Setor(int id, String nome, int buId, Date createdAt) {
		this.id = id;
		this.nome = nome;
		this.buId = buId;
		this.createdAt = createdAt;
	}

		public Setor(int id, String nome, int buId) {
			this.id = id;
			this.nome = nome;
			this.buId = buId;
		}


	// Getters e Setters
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

	public int getBuId() {
		return buId;
	}

	public void setBuId(int buId) {
		this.buId = buId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}