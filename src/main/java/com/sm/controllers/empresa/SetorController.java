package com.sm.controllers.empresa;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import com.sm.dao.sqlite.empresa.BUDAO;
import com.sm.dao.sqlite.empresa.SetorDAO;
import com.sm.models.empresa.BU;
import com.sm.models.empresa.Setor;
import com.sm.utils.callMessage;

public class SetorController implements Initializable {
  private final static callMessage messageHandler = new callMessage();

  @FXML
  private Button btnCadSetor;

  @FXML
  private ComboBox<BU> buNameBox;

  @FXML
  private TextField setorNameField;

  @FXML
  void btnCadSetorOnAction(ActionEvent event) {
    String setorName = setorNameField.getText();
    BU selectedBU = buNameBox.getValue(); // Obter o objeto BU selecionado

    if (selectedBU == null) {
      messageHandler.showAlert(Alert.AlertType.WARNING, "Atenção", "BU não selecionada",
          "Por favor, selecione uma BU.");

      return;
    }

    Setor objSetor = new Setor();
    SetorDAO setorDAO = new SetorDAO();

    try {
      setorDAO.connect();
      objSetor.setNome(setorName);
      objSetor.setBuId(selectedBU.getId());
      setorDAO.createSetor(objSetor);
      setorDAO.close();

      // Exibe um alerta de sucesso
      messageHandler.showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Cadastro realizado",
          "Setor foi cadastrado com sucesso.");
    } catch (Exception e) {
      messageHandler.showAlert(Alert.AlertType.ERROR, "Erro", "Falha ao cadastrar", 
      "Ocorreu um erro ao cadastrar o Setor." + e.getMessage());
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    carregarBUComboBox(); // Carregar as BUs na inicialização
  }

  private void carregarBUComboBox() {
    BUDAO buDAO = new BUDAO();
    try {
      buDAO.connect();
      List<BU> buList = buDAO.findAll();
      buNameBox.setItems(FXCollections.observableArrayList(buList));
      buDAO.close();
    } catch (Exception e) {
      messageHandler.showAlert(Alert.AlertType.ERROR, "Erro", "Falha ao carregar BUs", 
          "\"Ocorreu um erro ao carregar a lista de BUs" + e.getMessage());
    }
  }
}