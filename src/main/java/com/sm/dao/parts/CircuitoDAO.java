package com.sm.dao.parts;

import java.sql.*;
import java.util.Map;

import com.sm.database.sqlite.DatabaseConnection;
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
    DatabaseConnection.closeConnection();
  }

  // Criar tabela de circuitos
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

  // Criar tabela intermediária circuitos_componentes
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

  // Método para criar um circuito e seus componentes associados
  public void createCircuito(Circuito circuito) throws SQLException {
    // Inserir o circuito com created_at
    String sqlCircuito = "INSERT INTO circuitos (nome, ctf) VALUES (?, ?)";  //created_at + (?)

    try (PreparedStatement pstmtCircuito = conn.prepareStatement(sqlCircuito, Statement.RETURN_GENERATED_KEYS)) {
      pstmtCircuito.setString(1, circuito.getNome());
      pstmtCircuito.setString(2, circuito.getCtf());
      //pstmtCircuito.setTimestamp(3, Timestamp.valueOf(circuito.getCreatedAt())); // Usando Timestamp
      pstmtCircuito.executeUpdate();

      // Obtendo o ID do circuito inserido
      ResultSet generatedKeys = pstmtCircuito.getGeneratedKeys();
      if (generatedKeys.next()) {
        int circuitoId = generatedKeys.getInt(1);

        // Inserir ou associar os componentes do circuito com a quantidade
        for (Map.Entry<Componente, Integer> entry : circuito.getComponentesQuantidade().entrySet()) {
          Componente componente = entry.getKey();
          int quantidade = entry.getValue();
          int componenteId = getOrCreateComponente(componente);
          // Adicionando o ctf do componente e do circuito e a quantidade
          inserirComponenteNoCircuito(circuitoId, componenteId, componente.getCtf(), circuito.getCtf(), quantidade);
        }
      }
    }
  }

  // Método para verificar se o componente já existe com base no CTF e, se não,
  // criá-lo
  private int getOrCreateComponente(Componente componente) throws SQLException {
    // Verificar se o componente já existe
    String sqlSelect = "SELECT id FROM componentes WHERE ctf = ?";
    try (PreparedStatement pstmtSelect = conn.prepareStatement(sqlSelect)) {
      pstmtSelect.setString(1, componente.getCtf());
      ResultSet rs = pstmtSelect.executeQuery();

      if (rs.next()) {
        // Componente já existe, retornar o ID
        return rs.getInt("id");
      } else {
        // Componente não existe, criar um novo
        String sqlInsert = "INSERT INTO componentes (nome, ctf, preco, descricao) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {
          pstmtInsert.setString(1, componente.getNome());
          pstmtInsert.setString(2, componente.getCtf());
          pstmtInsert.setDouble(3, componente.getPreco());
          pstmtInsert.setString(4, componente.getDescricao());
          pstmtInsert.executeUpdate();

          ResultSet generatedKeys = pstmtInsert.getGeneratedKeys();
          if (generatedKeys.next()) {
            return generatedKeys.getInt(1); // Retornar o ID do novo componente
          }
        }
      }
    }
    return -1; // Erro: componente não pôde ser criado
  }

  // Método para associar um componente ao circuito
  private void inserirComponenteNoCircuito(int circuitoId, int componenteId, String componenteCtf, String circuitoCtf,
      int quantidade)
      throws SQLException {
    String sql = "INSERT INTO circuitos_componentes (circuito_id, componente_id, componente_ctf, circuito_ctf, quantidade) VALUES (?, ?, ?, ?, ?)";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, circuitoId);
      pstmt.setInt(2, componenteId);
      pstmt.setString(3, componenteCtf); // Adicionando o ctf do componente
      pstmt.setString(4, circuitoCtf); // Adicionando o ctf do circuito
      pstmt.setInt(5, quantidade); // Adicionando a quantidade
      pstmt.executeUpdate();
    }
  }
}