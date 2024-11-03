package com.sm.models.empresa;

public class Funcao {
    private int id;
    private String nome;

    // Construtor padrão
    public Funcao() {
    }

    // Construtor com parâmetros
    public Funcao(int id, String nome) {
        this.id = id;
        this.nome = nome;
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
}
