package com.sm.dao.parts;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import com.sm.database.DatabaseConnection;
import com.sm.models.parts.Circuito;
import com.sm.models.parts.Componente;

public class CircuitoDAO {
  private Connection conn;

  // Método para abrir a conexão
  public void connect() throws SQLException {
    conn = DatabaseConnection.getConnection();
  }

  // Método para fechar a conexão
  public void close() throws SQLException {
    if (conn != null && !conn.isClosed()) {
      conn.close();
    }
  }

  // Criar tabela de circuitos
  public void createTable() throws SQLException {
    String sqlCircuitos = "CREATE TABLE IF NOT EXISTS circuitos ("
        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "nome TEXT NOT NULL, "
        + "ctf TEXT NOT NULL"
        + ");";

    String sqlComponentesCircuito = "CREATE TABLE IF NOT EXISTS circuitos_componentes ("
        + "circuito_id INTEGER NOT NULL, "
        + "componente_id INTEGER NOT NULL, "
        + "FOREIGN KEY (circuito_id) REFERENCES circuitos(id), "
        + "FOREIGN KEY (componente_id) REFERENCES componentes(id)"
        + ");";

    try (Statement stmt = conn.createStatement()) {
      stmt.execute(sqlCircuitos);
      stmt.execute(sqlComponentesCircuito);
      System.out.println("Tabelas de circuitos e circuitos_componentes criadas com sucesso.");
    }
  }

  // Método para criar um circuito e seus componentes associados
  public void createCircuito(Circuito circuito) throws SQLException {
    // Inserir o circuito
    String sqlCircuito = "INSERT INTO circuitos (nome, ctf) VALUES (?, ?)";

    try (PreparedStatement pstmtCircuito = conn.prepareStatement(sqlCircuito, Statement.RETURN_GENERATED_KEYS)) {
      pstmtCircuito.setString(1, circuito.getNome());
      pstmtCircuito.setString(2, circuito.getCtf());
      pstmtCircuito.executeUpdate();

      // Obtendo o ID do circuito inserido
      ResultSet generatedKeys = pstmtCircuito.getGeneratedKeys();
      if (generatedKeys.next()) {
        int circuitoId = generatedKeys.getInt(1);

        // Inserir os componentes do circuito
        for (Componente componente : circuito.getComponentes()) {
          inserirComponenteNoCircuito(circuitoId, componente.getId());
        }
      }
    }
  }

  // Método auxiliar para inserir os componentes no circuito
  private void inserirComponenteNoCircuito(int circuitoId, int componenteId) throws SQLException {
    String sqlComponentesCircuito = "INSERT INTO circuitos_componentes (circuito_id, componente_id) VALUES (?, ?)";

    try (PreparedStatement pstmt = conn.prepareStatement(sqlComponentesCircuito)) {
      pstmt.setInt(1, circuitoId);
      pstmt.setInt(2, componenteId);
      pstmt.executeUpdate();
    }
  }

  // Método para buscar um circuito e seus componentes
  public Circuito getCircuitoById(int circuitoId) throws SQLException {
    String sqlCircuito = "SELECT nome, ctf FROM circuitos WHERE id = ?";
    Circuito circuito = null;

    try (PreparedStatement pstmt = conn.prepareStatement(sqlCircuito)) {
      pstmt.setInt(1, circuitoId);
      ResultSet rs = pstmt.executeQuery();

      if (rs.next()) {
        String nome = rs.getString("nome");
        String ctf = rs.getString("ctf");

        List<Componente> componentes = getComponentesByCircuitoId(circuitoId);
        circuito = new Circuito(nome, ctf, componentes);
      }
    }
    return circuito;
  }

  // Método auxiliar para buscar componentes de um circuito
  private List<Componente> getComponentesByCircuitoId(int circuitoId) throws SQLException {
    String sqlComponentes = "SELECT c.id, c.nome, c.ctf, c.preco, c.descricao "
        + "FROM componentes c "
        + "JOIN circuitos_componentes cc ON c.id = cc.componente_id "
        + "WHERE cc.circuito_id = ?";

    try (PreparedStatement pstmt = conn.prepareStatement(sqlComponentes)) {
      pstmt.setInt(1, circuitoId);
      ResultSet rs = pstmt.executeQuery();

      List<Componente> componentes = new ArrayList<>();
      while (rs.next()) {
        Componente componente = new Componente(
            rs.getInt("id"),
            rs.getString("nome"),
            rs.getString("ctf"),
            rs.getDouble("preco"),
            rs.getString("descricao"));
        componentes.add(componente);
      }
      return componentes;
    }
  }
}