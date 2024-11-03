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

public class FuncaoController implements Initializable {

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
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Sucesso");
      alert.setHeaderText("Cadastro realizado");
      alert.setContentText("A Funcao foi cadastrada com sucesso.");
      alert.showAndWait();
    } catch (Exception e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setHeaderText("Falha ao cadastrar");
			alert.setContentText("Ocorreu um erro ao cadastrar a Funcao." + e.getMessage());
			alert.showAndWait();
    }

  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    //
  }
}