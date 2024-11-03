package com.sm.dao.sqlite.empresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sm.database.sqlite.DatabaseConnection;
import com.sm.models.empresa.Funcao;

public class FuncaoDAO {
	private Connection conn;

	// Método para abrir a conexão
	public void connect() throws SQLException {
		conn = DatabaseConnection.getConnection();
	}

	// Método para fechar a conexão
	public void close() throws SQLException {
		DatabaseConnection.closeConnection();
	}

	// Criar tabela de Funções
	public void createTable() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS funcao ("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "nome TEXT NOT NULL"
				+ ");";

		try (Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
			System.out.println("Tabela de Funções criada com sucesso.");
		}
	}

	// Método para criar uma Função
	public void createFuncao(Funcao funcao) throws SQLException {
		String sql = "INSERT INTO funcao (nome) VALUES (?)";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, funcao.getNome());
			pstmt.executeUpdate();
			System.out.println("Função cadastrada com sucesso.");
		}
	}

	// Método para atualizar uma Função
	public void updateFuncao(Funcao funcao) throws SQLException {
		String sql = "UPDATE funcao SET nome = ? WHERE id = ?";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, funcao.getNome());
			pstmt.setInt(2, funcao.getId());
			pstmt.executeUpdate();
			System.out.println("Função atualizada com sucesso.");
		}
	}

	// Método para deletar uma Função
	public void deleteFuncao(int id) throws SQLException {
		String sql = "DELETE FROM funcao WHERE id = ?";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			System.out.println("Função deletada com sucesso.");
		}
	}

	public List<String> getAllFuncoes() throws SQLException {
		List<String> funcoes = new ArrayList<>();
		String sql = "SELECT nome FROM funcao";

		try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				funcoes.add(rs.getString("nome"));
			}
		}
		return funcoes;
	}
}