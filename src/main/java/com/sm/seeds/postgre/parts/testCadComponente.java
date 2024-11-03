package com.sm.seeds.postgre.parts;

import java.sql.SQLException;
import java.util.List;

import com.sm.dao.postgre.parts.ComponenteDAO;
import com.sm.models.parts.Componente;

public class testCadComponente {

  public static void main(String[] args) {
    cadComponente();
    consultComponente();
  }

  public static void cadComponente() {
    Componente componente = new Componente();
    ComponenteDAO componenteDAO = new ComponenteDAO();

    try {
      componenteDAO.connect();
      componenteDAO.createTable();

      componente.setNome("Resistor");
      componente.setCtf("CTF001");
      componente.setDescricao("Resistor de 10 Ohms");
      componente.setPreco(0.50);
      componenteDAO.createComponente(componente);

    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Erro ao inserir um Componente.");
    } finally {
      try {
        componenteDAO.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static void consultComponente() {
    ComponenteDAO componenteDAO = new ComponenteDAO();

    try {
      componenteDAO.connect();

      // Chama o método para buscar todos os componentes
      List<Componente> componentes = componenteDAO.getAllComponentes();

      // Exibe os dados dos componentes
      for (Componente componente : componentes) {
        System.out.println("ID: " + componente.getId());
        System.out.println("Nome: " + componente.getNome());
        System.out.println("CTF: " + componente.getCtf());
        System.out.println("Descrição: " + componente.getDescricao());
        System.out.println("Preço: " + componente.getPreco());
        System.out.println("Data de Criação: " + componente.getCreatedAt());
        System.out.println("-------------------------------------");
      }

    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Erro ao consultar os componentes.");
    } finally {
      try {
        componenteDAO.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}