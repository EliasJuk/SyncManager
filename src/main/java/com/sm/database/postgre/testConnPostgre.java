package com.sm.database.postgre;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class testConnPostgre {
  // Método para abrir a conexão com o banco usando a classe PostgreConn
  public void connect() throws SQLException {
    PostgreConn.getConnection();
    System.out.println("Conexão com PostgreSQL aberta com sucesso.");
  }

  // Método para fechar a conexão usando a classe PostgreConn
  public void close() throws SQLException {
    PostgreConn.closeConnection();
    System.out.println("Conexão com PostgreSQL fechada com sucesso.");
  }

  public static void main(String[] args) {
    testConnPostgre test = new testConnPostgre();

    try {
      // Abrindo a conexão
      test.connect();

      // Obtém a conexão para usar em operações SQL
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

    } catch (SQLException e) {
      System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
    } finally {
      try {
        // Fechando a conexão
        test.close();
      } catch (SQLException e) {
        System.err.println("Erro ao fechar a conexão: " + e.getMessage());
      }
    }
  }
}