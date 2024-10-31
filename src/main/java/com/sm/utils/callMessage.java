package com.sm.utils;

import javafx.scene.control.Alert;

public class callMessage {

  public void showAlert(String title, String header, String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(message);
    alert.showAndWait();
  }

  public void showAlert(String title, String header, String context, String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(context + " " + message);
    alert.showAndWait();
  }
}