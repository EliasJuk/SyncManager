package com.sm.database.postgre;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class testConnPostgre {
  public static void main(String[] args) {
    try {
      // Abre a conexão com o banco de dados
      Connection conn = PostgreConn.getConnection();

      // Cria um Statement para executar comandos SQL
      Statement stmt = conn.createStatement();

      // Cria uma tabela para teste, se não existir
      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS pessoas ("
          + "id SERIAL PRIMARY KEY, "
          + "nome VARCHAR(100), "
          + "idade INT)");

      // Insere dados de teste
      stmt.executeUpdate("INSERT INTO pessoas (nome, idade) VALUES ('João', 30)");
      stmt.executeUpdate("INSERT INTO pessoas (nome, idade) VALUES ('Maria', 25)");

      // Executa uma consulta para recuperar os dados
      String query = "SELECT * FROM pessoas";
      ResultSet rs = stmt.executeQuery(query);

      // Processa o resultado da consulta
      while (rs.next()) {
        System.out.println("ID: " + rs.getInt("id"));
        System.out.println("Nome: " + rs.getString("nome"));
        System.out.println("Idade: " + rs.getInt("idade"));
        System.out.println("---------------------------");
      }

      // Fecha o ResultSet e o Statement
      rs.close();
      stmt.close();

      // Fecha a conexão com o banco de dados
      PostgreConn.closeConnection();

    } catch (SQLException e) {
      System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
    }
  }
}
