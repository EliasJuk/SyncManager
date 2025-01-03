package com.sm.database.sqlite;

import java.sql.SQLException;

public class testConnSqlite {
  // Método para abrir a conexão com o banco usando a classe DatabaseConn
  public void connect() throws SQLException {
    DatabaseConnection.getConnection();
    System.out.println("Conexão aberta com sucesso.");
  }

  // Método para fechar a conexão usando a classe DatabaseConn
  public void close() throws SQLException {
    DatabaseConnection.closeConnection();
    System.out.println("Conexão fechada com sucesso.");
  }

  public static void main(String[] args) {
    testConnSqlite test = new testConnSqlite();

    try {
      // Abrindo a conexão
      test.connect();
    } catch (SQLException e) {
      System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
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