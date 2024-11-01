package com.sm.models.parts;

import java.time.LocalDateTime;
import java.util.Map;

public class Circuito {
  private String nome;
  private String ctf;
  private LocalDateTime createdAt;
  private Map<Componente, Integer> componentesQuantidade;

  // Construtor vazio
  public Circuito() {
    this.createdAt = LocalDateTime.now();
  }

  // Construtor
  public Circuito(String nome, String ctf, Map<Componente, Integer> componentesQuantidade) {
    this.nome = nome;
    this.ctf = ctf;
    this.componentesQuantidade = componentesQuantidade;
    this.createdAt = LocalDateTime.now();
  }

  // Getters
  public String getNome() {
    return nome;
  }

  public String getCtf() {
    return ctf;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public Map<Componente, Integer> getComponentesQuantidade() {
    return componentesQuantidade;
  }
}