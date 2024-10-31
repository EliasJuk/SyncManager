package com.sm.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.sm.Routes;
import com.sm.utils.callMessage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class loginController implements Initializable {
  private final static callMessage messageHandler = new callMessage();

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
        messageHandler.showAlert("Erro", "Erro", "Erro ao tentar realizar o Login", e.getMessage());
      }
    } else {
      messageHandler.showAlert("Erro", "Login ou Senha Incorreto", "Erro ao tentar realizar o Login", "Falha ao tentar Logar");
    }

  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {

  }
}