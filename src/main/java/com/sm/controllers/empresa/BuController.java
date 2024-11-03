package com.sm.controllers.empresa;

import java.net.URL;
import java.util.ResourceBundle;

import com.sm.dao.sqlite.empresa.BUDAO;
import com.sm.models.empresa.BU;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import com.sm.utils.callMessage;

public class BuController implements Initializable{
  private final static callMessage messageHandler = new callMessage();

  @FXML
  private Button btnSalvar;

  @FXML
  private TextField buNameField;

  @FXML
  void btnSalvarOnAction(ActionEvent event) {
    String buName = buNameField.getText();

    BU objBU = new BU();
    BUDAO cadBuDao = new BUDAO();

    try {
      cadBuDao.connect();
      objBU.setNome(buName);
			cadBuDao.createBU(objBU);
      cadBuDao.close();

      // Exibe um alerta de sucesso
      messageHandler.showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Cadastro realizado",
      "A BU foi cadastrada com sucesso.");
      
      buNameField.clear(); // Limpa o campo de texto
    } catch (Exception e) {
      messageHandler.showAlert(Alert.AlertType.ERROR, "Erro", "Falha ao cadastrar", 
      "Ocorreu um erro ao cadastrar a BU." + e.getMessage());
    }
  }


  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // Inicialização, se necessário
  }
}