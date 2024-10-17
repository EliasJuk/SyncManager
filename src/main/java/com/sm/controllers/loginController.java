package com.sm.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.sm.Routes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class loginController implements Initializable {

  @FXML
  private Button btnLogin;

  @FXML
  private PasswordField passwordInput;

  @FXML
  private TextField userInput;

  @FXML
  void loginOnAction(ActionEvent event) {
    String usuario = passwordInput.getText();
    String senha = passwordInput.getText();

    if (usuario.equals("admin") && senha.equals("admin")) {
      System.out.println("Login OK");

      Stage stage = (Stage) btnLogin.getScene().getWindow();
      stage.close();

      try {
        Routes.mainView(loginController.class);
      } catch (Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Erro ao tentar realizar o Login");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
      }
    } else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Erro");
      alert.setHeaderText("Login ou Senha Incorreto");
      alert.setContentText("Digite a senha novamnte");
      alert.showAndWait();
    }

  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {

  }
}