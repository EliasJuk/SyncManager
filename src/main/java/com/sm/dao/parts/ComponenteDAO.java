package com.sm.dao.parts;

import java.sql.*;
import com.sm.database.DatabaseConnection;
import com.sm.models.parts.Componente;

public class ComponenteDAO {
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

    // Criar tabela de componentes
    public void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS componentes ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "nome TEXT NOT NULL, "
                    + "ctf TEXT NOT NULL UNIQUE, "
                    + "preco REAL NOT NULL, "
                    + "descricao TEXT"
                    + ");";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela de componentes criada com sucesso.");
        }
    }

    // Método para verificar se o CTF já existe
    public boolean ctfExists(String ctf) throws SQLException {
        String sql = "SELECT id FROM componentes WHERE ctf = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ctf);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();  // Retorna true se o CTF já existir
        }
    }

    // Método para criar um componente
    public void createComponente(Componente componente) throws SQLException {
        if (ctfExists(componente.getCtf())) {
            System.out.println("Não foi possível cadastrar. Esse CTF já existe.");
        } else {
            String sql = "INSERT INTO componentes (nome, ctf, preco, descricao) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, componente.getNome());
                pstmt.setString(2, componente.getCtf());
                pstmt.setDouble(3, componente.getPreco());
                pstmt.setString(4, componente.getDescricao());
                pstmt.executeUpdate();
                System.out.println("Componente cadastrado com sucesso.");
            }
        }
    }
}