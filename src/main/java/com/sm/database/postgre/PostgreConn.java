package com.sm.database.postgre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreConn {
  private static final String DB_NAME = "syncmanager";    // Nome do banco de dados
  private static final String DB_HOST = "localhost";      // Host do banco de dados
  private static final String DB_PORT = "5432";           // Porta do banco de dados PostgreSQL
  private static final String DB_USER = "postgres";       // Usuário do banco de dados
  private static final String DB_PASSWORD = "password";   // Senha do banco de dados
  private static Connection conn = null;

  // Método para abrir a conexão com o banco de dados
  public static Connection getConnection() throws SQLException {
    if (conn == null || conn.isClosed()) {
      try {
        // Constrói a URL de conexão para PostgreSQL
        String url = "jdbc:postgresql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;

        // Abre a conexão com o banco de dados usando usuário e senha
        conn = DriverManager.getConnection(url, DB_USER, DB_PASSWORD);
      } catch (SQLException e) {
        System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        throw new SQLException("Não foi possível conectar ao banco de dados", e);
      }
    }
    return conn;
  }

  // Método para fechar a conexão com o banco de dados
  public static void closeConnection() throws SQLException {
    if (conn != null && !conn.isClosed()) {
      conn.close();
    }
  }
}