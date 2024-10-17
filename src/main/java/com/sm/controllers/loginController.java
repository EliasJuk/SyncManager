package com.sm.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class loginController implements Initializable{

    
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

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }
}