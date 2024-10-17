package com.sm.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.sm.models.Usuario;
import com.sm.database.DatabaseConnection;

public class UsuarioDAO {

    // Método para adicionar um novo usuário no banco de dados
    public void adicionarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (user, senha, data_criacao) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getUser());
            stmt.setString(2, hashPassword(usuario.getSenha())); // Aplica o hash na senha
            stmt.setObject(3, usuario.getDataCriacao());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
