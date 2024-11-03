package com.sm.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.sm.Routes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class mainController implements Initializable{

  @FXML
  private MenuItem munuItemPresenca;

  @FXML
  private MenuItem miEmpresaBU;

  @FXML
  void miEmpresaBuAction(ActionEvent event) {
    Routes.showViewBU(mainController.class);
  }

  @FXML
  void miPresencaOnAction(ActionEvent event) {
    Routes.showViewPresenca(mainController.class);
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {

  }
}