package com.sm.database.sqlite;

import java.io.File;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
  private static final String DB_NAME = "database.db"; // Nome do banco de dados
  private static Connection conn = null;

  // Método para abrir a conexão com o banco de dados
  public static Connection getConnection() throws SQLException {
    if (conn == null || conn.isClosed()) {
      try {
        // Obtém o caminho do diretório onde o JAR está localizado
        URI uri = DatabaseConnection.class.getProtectionDomain().getCodeSource().getLocation().toURI();
        String jarDir = new File(uri).getParent();

        // Constrói o caminho completo para o banco de dados
        String dbPath = jarDir + File.separator + DB_NAME;

        // Constrói a URL do banco de dados SQLite
        String url = "jdbc:sqlite:" + dbPath;

        // Abre a conexão com o banco de dados
        conn = DriverManager.getConnection(url);
      } catch (Exception e) {
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