package com.sm.database.migrations.parts;

import java.sql.SQLException;
import com.sm.dao.parts.ComponenteDAO;
import com.sm.models.parts.Componente;

public class testCadComponente {
  public static void main(String[] args) {
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
}