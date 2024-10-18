package com.sm.models.parts;

import java.util.List;

public class Circuito {
  private String nome;
  private String ctf;
  private List<Componente> componentes;

  // Construtor vazio
  public Circuito() {
  }

  // Construtor
  public Circuito(String nome, String ctf, List<Componente> componentes) {
    this.nome = nome;
    this.ctf = ctf;
    this.componentes = componentes;
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

  public List<Componente> getComponentes() {
    return componentes;
  }

  public void setComponentes(List<Componente> componentes) {
    this.componentes = componentes;
  }
}