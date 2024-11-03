package com.sm.dao.sqlite.empresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sm.database.sqlite.DatabaseConnection;
import com.sm.models.empresa.BU;

public class BUDAO {
  private Connection conn;

  // Método para abrir a conexão
  public void connect() throws SQLException {
    conn = DatabaseConnection.getConnection();
  }

  // Método para fechar a conexão
  public void close() throws SQLException {
    DatabaseConnection.closeConnection();
  }

  // Criar tabela de BUs
  public void createTable() throws SQLException {
    String sql = "CREATE TABLE IF NOT EXISTS bu ("
        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "nome TEXT NOT NULL"
        + ");";

    try (Statement stmt = conn.createStatement()) {
      stmt.execute(sql);
      System.out.println("Tabela de BUs criada com sucesso.");
    }
  }

  // Método para criar uma BU
  public void createBU(BU bu) throws SQLException {
    String sql = "INSERT INTO bu (nome) VALUES (?)";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, bu.getNome());
      pstmt.executeUpdate();
      System.out.println("BU cadastrada com sucesso.");
    }
  }

  // Método para atualizar uma BU
  public void updateBU(BU bu) throws SQLException {
    String sql = "UPDATE bu SET nome = ? WHERE id = ?";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, bu.getNome());
      pstmt.setInt(2, bu.getId());
      pstmt.executeUpdate();
      System.out.println("BU atualizada com sucesso.");
    }
  }

  // Método para deletar uma BU
  public void deleteBU(int id) throws SQLException {
    String sql = "DELETE FROM bu WHERE id = ?";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      pstmt.executeUpdate();
      System.out.println("BU deletada com sucesso.");
    }
  }

  public List<BU> findAll() throws SQLException {
    List<BU> buList = new ArrayList<>();
    String sql = "SELECT id, nome FROM bu";

    try (Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
      while (rs.next()) {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        buList.add(new BU(id, nome));
      }
    }
    return buList;
  }
}