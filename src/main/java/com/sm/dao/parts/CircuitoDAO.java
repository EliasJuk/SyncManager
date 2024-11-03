package com.sm.dao.parts;

import java.sql.*;
import java.util.Map;
import com.sm.database.sqlite.DatabaseConnection;
import com.sm.models.parts.Circuito;
import com.sm.models.parts.Componente;

public class CircuitoDAO {
  private Connection conn;

  public void connect() throws SQLException {
    conn = DatabaseConnection.getConnection();
  }

  public void close() throws SQLException {
    DatabaseConnection.closeConnection();
  }

  public void createTable() throws SQLException {
    String sql = "CREATE TABLE IF NOT EXISTS circuitos ("
        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "nome TEXT NOT NULL, "
        + "ctf TEXT NOT NULL, "
        + "created_at DATETIME DEFAULT CURRENT_TIMESTAMP"
        + ");";

    try (Statement stmt = conn.createStatement()) {
      stmt.execute(sql);
      System.out.println("Tabela de circuitos criada com sucesso.");
    }
  }

  public void createTableCircuitoComponente() throws SQLException {
    String sql = "CREATE TABLE IF NOT EXISTS circuitos_componentes ("
        + "circuito_id INTEGER NOT NULL, "
        + "componente_id INTEGER NOT NULL, "
        + "componente_ctf TEXT NOT NULL, "
        + "circuito_ctf TEXT NOT NULL, "
        + "quantidade INTEGER NOT NULL, "
        + "PRIMARY KEY (circuito_id, componente_id), "
        + "FOREIGN KEY (circuito_id) REFERENCES circuitos(id), "
        + "FOREIGN KEY (componente_id) REFERENCES componentes(id)"
        + ");";

    try (Statement stmt = conn.createStatement()) {
      stmt.execute(sql);
      System.out.println("Tabela de circuitos_componentes criada com sucesso.");
    }
  }

  public void createCircuito(Circuito circuito) throws SQLException {
    String sqlCircuito = "INSERT INTO circuitos (nome, ctf, created_at) VALUES (?, ?, ?)";

    try (PreparedStatement pstmtCircuito = conn.prepareStatement(sqlCircuito, Statement.RETURN_GENERATED_KEYS)) {
      pstmtCircuito.setString(1, circuito.getNome());
      pstmtCircuito.setString(2, circuito.getCtf());
      pstmtCircuito.setTimestamp(3,
          circuito.getCreatedAt() != null ? Timestamp.valueOf(circuito.getCreatedAt()) : null);
      pstmtCircuito.executeUpdate();

      ResultSet generatedKeys = pstmtCircuito.getGeneratedKeys();
      if (generatedKeys.next()) {
        int circuitoId = generatedKeys.getInt(1);

        for (Map.Entry<Componente, Integer> entry : circuito.getComponentesQuantidade().entrySet()) {
          Componente componente = entry.getKey();
          int quantidade = entry.getValue();
          int componenteId = getOrCreateComponente(componente);
          inserirComponenteNoCircuito(circuitoId, componenteId, componente.getCtf(), circuito.getCtf(), quantidade);
        }
      }
    }
  }

  private int getOrCreateComponente(Componente componente) throws SQLException {
    String sqlSelect = "SELECT id FROM componentes WHERE ctf = ?";
    try (PreparedStatement pstmtSelect = conn.prepareStatement(sqlSelect)) {
      pstmtSelect.setString(1, componente.getCtf());
      ResultSet rs = pstmtSelect.executeQuery();

      if (rs.next()) {
        return rs.getInt("id");
      } else {
        String sqlInsert = "INSERT INTO componentes (nome, ctf, preco, descricao) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {
          pstmtInsert.setString(1, componente.getNome());
          pstmtInsert.setString(2, componente.getCtf());
          pstmtInsert.setDouble(3, componente.getPreco());
          pstmtInsert.setString(4, componente.getDescricao());
          pstmtInsert.executeUpdate();

          ResultSet generatedKeys = pstmtInsert.getGeneratedKeys();
          if (generatedKeys.next()) {
            return generatedKeys.getInt(1);
          }
        }
      }
    }
    return -1;
  }

  private void inserirComponenteNoCircuito(int circuitoId, int componenteId, String componenteCtf, String circuitoCtf,
      int quantidade)
      throws SQLException {
    String sql = "INSERT INTO circuitos_componentes (circuito_id, componente_id, componente_ctf, circuito_ctf, quantidade) VALUES (?, ?, ?, ?, ?)";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, circuitoId);
      pstmt.setInt(2, componenteId);
      pstmt.setString(3, componenteCtf);
      pstmt.setString(4, circuitoCtf);
      pstmt.setInt(5, quantidade);
      pstmt.executeUpdate();
    }
  }
}