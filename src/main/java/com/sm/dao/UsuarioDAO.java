package com.sm.dao;

import java.sql.*;
import com.sm.database.DatabaseConnection;
import com.sm.models.Usuario;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UsuarioDAO {
    private Connection conn;

    // Método para abrir a conexão com o banco usando a classe DatabaseConnection
    public void connect() throws SQLException {
        conn = DatabaseConnection.getConnection();
    }

    // Método para fechar a conexão usando a classe DatabaseConnection
    public void close() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }  

    // Método para criar a tabela de usuários
    public void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS usuarios ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "user TEXT UNIQUE NOT NULL, "
                    + "senha TEXT NOT NULL, "
                    + "data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                    + ");";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela de usuários criada com sucesso.");
        }
    }

    // Método para criar um usuário (CREATE)
    public void createUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (user, senha) VALUES (?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getUser());
            pstmt.setString(2, hashPassword(usuario.getSenha())); // Armazenar senha como hash
            pstmt.executeUpdate();
        }
    }

    // Método para ler (READ) um usuário por user
    public Usuario readUsuario(String user) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE user = ?";
        Usuario usuario = null;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                usuario = new Usuario(rs.getString("user"), rs.getString("senha"));
                usuario.setDataCriacao(rs.getTimestamp("data_criacao").toLocalDateTime());
            }
        }
        return usuario;
    }

    // Método para atualizar um usuário (UPDATE)
    public void updateUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuarios SET senha = ? WHERE user = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, hashPassword(usuario.getSenha()));
            pstmt.setString(2, usuario.getUser());
            pstmt.executeUpdate();
        }
    }

    // Método para deletar um usuário (DELETE)
    public void deleteUsuario(String user) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE user = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user);
            pstmt.executeUpdate();
        }
    }

    // Método para fazer hash da senha (SHA-256)
    private String hashPassword(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(senha.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar hash da senha.", e);
        }
    }
}