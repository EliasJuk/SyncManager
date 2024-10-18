package com.sm.models.parts;

public class Componente {
  private int id;
  private String nome;
  private String ctf;
  private double preco;
  private String descricao;

  // Construtor Vazio
  public Componente(){}

  // Construtor com o ID
  public Componente(int id, String nome, String ctf, double preco, String descricao) {
    this.id = id;
    this.nome = nome;
    this.ctf = ctf;
    this.preco = preco;
    this.descricao = descricao;
  }

  // Construtor sem o ID
  public Componente(String nome, String ctf, double preco, String descricao) {
    this.nome = nome;
    this.ctf = ctf;
    this.preco = preco;
    this.descricao = descricao;
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

  public String getCtf() {
    return ctf;
  }

  public void setCtf(String ctf) {
    this.ctf = ctf;
  }

  public double getPreco() {
    return preco;
  }

  public void setPreco(double preco) {
    this.preco = preco;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }
}
