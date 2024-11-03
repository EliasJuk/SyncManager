package com.sm.seeds.sqlite.pessoa;

import com.sm.dao.sqlite.pessoas.UsuarioDAO;
import com.sm.models.Usuario;
import java.sql.SQLException;

public class testUsuario {
  public static void main(String[] args) {
    UsuarioDAO usuarioDAO = new UsuarioDAO();

    try {
      // Conectar ao banco de dados
      usuarioDAO.connect();
      // Criar a tabela de usuários
      usuarioDAO.createTable();

      // Criar um novo usuário
      Usuario novoUsuario = new Usuario("usuario1", "senha123");
      usuarioDAO.createUsuario(novoUsuario);
      System.out.println("Usuário criado com sucesso: " + novoUsuario.getUser());

      // Ler o usuário
      Usuario usuarioLido = usuarioDAO.readUsuario("usuario1");
      System.out.println("Usuário lido: " + usuarioLido.getUser());

      // Atualizar o usuário
      usuarioLido.setSenha("novaSenha456");
      usuarioDAO.updateUsuario(usuarioLido);
      System.out.println("Senha atualizada para o usuário: " + usuarioLido.getUser());

      // Ler novamente para verificar a atualização
      Usuario usuarioAtualizado = usuarioDAO.readUsuario("usuario1");
      System.out.println("Senha após atualização: " + usuarioAtualizado.getSenha());

      // Deletar o usuário
      //usuarioDAO.deleteUsuario("usuario1");
      //System.out.println("Usuário deletado: usuario1");

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      // Fechar a conexão
      try {
        usuarioDAO.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}