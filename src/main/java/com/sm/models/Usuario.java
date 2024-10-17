package com.sm.models;

import java.time.LocalDateTime;

public class Usuario {
  private String user;
  private String senha;
  private LocalDateTime dataCriacao;

  public Usuario(String user, String senha) {
    this.user = user;
    this.senha = senha;
    this.dataCriacao = LocalDateTime.now(); // Define a data de criação como agora
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public LocalDateTime getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(LocalDateTime dataCriacao) {
    this.dataCriacao = dataCriacao;
  }
}