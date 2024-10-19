package com.sm.models.parts;

import java.util.Map;

public class Circuito {
  private String nome;
  private String ctf;
  // Map que armazena os componentes e suas respectivas quantidades
  private Map<Componente, Integer> componentesQuantidade;

  // Construtor vazio
  public Circuito() {
  }

  // Construtor
  public Circuito(String nome, String ctf, Map<Componente, Integer> componentesQuantidade) {
    this.nome = nome;
    this.ctf = ctf;
    this.componentesQuantidade = componentesQuantidade;
  }

  // Getters e Setters
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

  public Map<Componente, Integer> getComponentesQuantidade() {
    return componentesQuantidade;
  }

  public void setComponentesQuantidade(Map<Componente, Integer> componentesQuantidade) {
    this.componentesQuantidade = componentesQuantidade;
  }
}