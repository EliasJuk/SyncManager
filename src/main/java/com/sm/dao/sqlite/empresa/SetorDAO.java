package com.sm.dao.sqlite.empresa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.sm.database.sqlite.DatabaseConnection;
import com.sm.models.empresa.Setor;

public class SetorDAO {
  private Connection conn;

  // Método para abrir a conexão
  public void connect() throws SQLException {
    conn = DatabaseConnection.getConnection();
  }

  // Método para fechar a conexão
  public void close() throws SQLException {
    DatabaseConnection.closeConnection();
  }

  // Criar tabela de Setores
  public void createTable() throws SQLException {
    String sql = "CREATE TABLE IF NOT EXISTS setor ("
        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "nome TEXT NOT NULL, "
        + "created_at DATE DEFAULT (date('now')), " // Usando DATE para a data de criação
        + "bu_id INTEGER, "
        + "FOREIGN KEY (bu_id) REFERENCES bu(id) ON DELETE CASCADE"
        + ");";

    try (Statement stmt = conn.createStatement()) {
      stmt.execute(sql);
      System.out.println("Tabela de Setores criada com sucesso.");
    }
  }

  // Método para criar um Setor
  public void createSetor(Setor setor) throws SQLException {
    String sql = "INSERT INTO setor (nome, bu_id, created_at) VALUES (?, ?, ?)";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, setor.getNome());
      pstmt.setInt(2, setor.getBuId());
      pstmt.setDate(3, setor.getCreatedAt()); // Usando java.sql.Date
      pstmt.executeUpdate();
      System.out.println("Setor cadastrado com sucesso.");
    }
  }

  // Método para atualizar um Setor
  public void updateSetor(Setor setor) throws SQLException {
    String sql = "UPDATE setor SET nome = ?, bu_id = ?, created_at = ? WHERE id = ?";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, setor.getNome());
      pstmt.setInt(2, setor.getBuId());
      pstmt.setDate(3, setor.getCreatedAt()); // Atualiza a data de criação se necessário
      pstmt.setInt(4, setor.getId());
      pstmt.executeUpdate();
      System.out.println("Setor atualizado com sucesso.");
    }
  }

  // Método para deletar um Setor
  public void deleteSetor(int id) throws SQLException {
    String sql = "DELETE FROM setor WHERE id = ?";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      pstmt.executeUpdate();
      System.out.println("Setor deletado com sucesso.");
    }
  }

  // Método para buscar Setor por ID
  public Setor findSetorById(int id) throws SQLException {
    String sql = "SELECT * FROM setor WHERE id = ?";
    Setor setor = null;

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      ResultSet rs = pstmt.executeQuery();

      if (rs.next()) {
        setor = new Setor(
            rs.getInt("id"),
            rs.getString("nome"),
            rs.getInt("bu_id"));
      }
    }

    return setor;
  }

  // Método para buscar setores pelo ID da BU
  public List<Setor> findByBUId(int buId) throws SQLException {
    String sql = "SELECT * FROM setor WHERE bu_id = ?";
    List<Setor> setores = new ArrayList<>();

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, buId);
      ResultSet rs = pstmt.executeQuery();

      while (rs.next()) {
        Setor setor = new Setor(
            rs.getInt("id"),
            rs.getString("nome"),
            rs.getInt("bu_id"));
        setores.add(setor);
      }
    }

    return setores;
  }
}
