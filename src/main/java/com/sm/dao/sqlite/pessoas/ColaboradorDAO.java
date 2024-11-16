package com.sm.dao.sqlite.pessoas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sm.database.sqlite.DatabaseConnection;
import com.sm.models.pessoas.Colaborador;

public class ColaboradorDAO {

	private Connection conn;

	// Método para abrir a conexão com o banco usando a classe DatabaseConn
	public void connect() throws SQLException {
		conn = DatabaseConnection.getConnection();
	}

	// Método para fechar a conexão usando a classe DatabaseConn
	public void close() throws SQLException {
		DatabaseConnection.closeConnection();
	}

	// Criar tabela de colaboradores
	public void createTable() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS colaboradores ("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "nome TEXT NOT NULL, "
				+ "data_nascimento TEXT NOT NULL, "
				+ "matricula TEXT NOT NULL, "
				+ "telefone TEXT NOT NULL, "
				+ "telefone_contato TEXT, "
				+ "endereco TEXT NOT NULL, "
				+ "cep TEXT, "
				+ "bu TEXT NOT NULL, "
				+ "setor TEXT NOT NULL, "
				+ "funcao TEXT NOT NULL"
				+ ");";

		try (Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
			System.out.println("Tabela de colaboradores criada com sucesso.");
		}
	}

	// Método para criar um colaborador
	public void createColaborador(Colaborador colaborador) throws SQLException {
		String sql = "INSERT INTO colaboradores (nome, data_nascimento, matricula, telefone, telefone_contato, endereco, cep, bu, setor, funcao) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, colaborador.getNome());
			pstmt.setString(2, colaborador.getDataNascimento());
			pstmt.setString(3, colaborador.getMatricula());
			pstmt.setString(4, colaborador.getTelefone());
			pstmt.setString(5, colaborador.getTelefoneContato());
			pstmt.setString(6, colaborador.getEndereco());
			pstmt.setString(7, colaborador.getCep());
			pstmt.setString(8, colaborador.getBu());
			pstmt.setString(9, colaborador.getSetor());
			pstmt.setString(10, colaborador.getFuncao());
			pstmt.executeUpdate();
		}
	}

	// Método para ler um colaborador por ID
	public Colaborador readColaborador(int id) throws SQLException {
		String sql = "SELECT * FROM colaboradores WHERE id = ?";
		Colaborador colaborador = null;

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				colaborador = new Colaborador("Colaborador 1");
				colaborador.setId(rs.getInt("id"));
				colaborador.setNome(rs.getString("nome"));
				colaborador.setDataNascimento(rs.getString("data_nascimento"));
				colaborador.setMatricula(rs.getString("matricula"));
				colaborador.setTelefone(rs.getString("telefone"));
				colaborador.setTelefoneContato(rs.getString("telefone_contato"));
				colaborador.setEndereco(rs.getString("endereco"));
				colaborador.setCep(rs.getString("cep"));
				colaborador.setBu(rs.getString("bu"));
				colaborador.setSetor(rs.getString("setor"));
				colaborador.setFuncao(rs.getString("funcao"));
			}
		}
		return colaborador;
	}

	// Método para atualizar um colaborador
	public void updateColaborador(Colaborador colaborador) throws SQLException {
		String sql = "UPDATE colaboradores SET nome = ?, data_nascimento = ?, matricula = ?, telefone = ?, "
				+ "telefone_contato = ?, endereco = ?, cep = ?, bu = ?, setor = ?, funcao = ? WHERE id = ?";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, colaborador.getNome());
			pstmt.setString(2, colaborador.getDataNascimento());
			pstmt.setString(3, colaborador.getMatricula());
			pstmt.setString(4, colaborador.getTelefone());
			pstmt.setString(5, colaborador.getTelefoneContato());
			pstmt.setString(6, colaborador.getEndereco());
			pstmt.setString(7, colaborador.getCep());
			pstmt.setString(8, colaborador.getBu());
			pstmt.setString(9, colaborador.getSetor());
			pstmt.setString(10, colaborador.getFuncao());
			pstmt.setInt(11, colaborador.getId());
			pstmt.executeUpdate();
		}
	}

	// Método para deletar um colaborador
	public void deleteColaborador(int id) throws SQLException {
		String sql = "DELETE FROM colaboradores WHERE id = ?";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		}
	}

	public List<Colaborador> selectAll() {
		List<Colaborador> colaboradores = new ArrayList<>();
		String sql = "SELECT nome, matricula FROM colaboradores";

		try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				String nome = rs.getString("nome");
				String matricula = rs.getString("matricula");
				colaboradores.add(new Colaborador(nome, matricula)); // Supondo que você tenha um construtor que aceita nome e matrícula
			}
		} catch (Exception e) {
			e.printStackTrace(); // Lidar com exceções conforme necessário
		}
		return colaboradores;
	}

	public boolean hasColaboradores() {
		String sql = "SELECT COUNT(*) FROM colaboradores"; // Substitua pelo nome correto da tabela
		try (PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				return rs.getInt(1) > 0; // Retorna true se houver pelo menos um registro
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Trate a exceção conforme necessário
		}
		return false; // Retorna false em caso de erro ou se não houver registros
	}
}