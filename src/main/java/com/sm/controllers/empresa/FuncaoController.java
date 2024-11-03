package com.sm.controllers.empresa;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import com.sm.dao.sqlite.empresa.FuncaoDAO;
import com.sm.models.empresa.Funcao;
import com.sm.utils.callMessage;

public class FuncaoController implements Initializable {
  private final static callMessage messageHandler = new callMessage();

  @FXML
  private Button btnSalvar;

  @FXML
  private TextField funcaoNameField;

  @FXML
  void btnSalvarOnAction(ActionEvent event) {
    String funcaoName = funcaoNameField.getText();

    Funcao objFuncao = new Funcao();
    FuncaoDAO funcaoDAO = new FuncaoDAO();

    try {
      funcaoDAO.connect();
      objFuncao.setNome(funcaoName);
      funcaoDAO.createFuncao(objFuncao);
      funcaoDAO.close();

      // Exibe um alerta de sucesso
      messageHandler.showAlert(Alert.AlertType.ERROR, "Sucesso", "Cadastro realizado", 
      "\"A Funcao foi cadastrada com sucesso.");
    } catch (Exception e) {
      messageHandler.showAlert(Alert.AlertType.ERROR, "Erro", "Falha ao cadastrar", 
      "\"Ocorreu um erro ao cadastrar a Funcao." + e.getMessage());
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) { }
}