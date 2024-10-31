package com.sm;

import com.sm.utils.callMessage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Routes {
  private final static callMessage messageHandler = new callMessage();

  @SuppressWarnings("exports")
  public static void Login(Class<?> clazz, Stage primaryStage) throws Exception {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(Routes.class.getResource("/com/sm/views/viewLogin.fxml"));
      Parent root = fxmlLoader.load();
      Scene login = new Scene(root);

      primaryStage.setTitle("Login");
      primaryStage.setScene(login);
      primaryStage.show();
    } catch (Exception e) {
      messageHandler.showAlert("Erro", "Erro", "Não foi possivel carregar a tela de Login", e.getMessage());
    }
  }

  public static void mainView(Class<?> clazz) throws Exception {
    try {
      Parent root = FXMLLoader.load(clazz.getResource("/com/sm/views/viewMain.fxml"));
      Stage stageMain = new Stage();
      Scene scene = new Scene(root);

      stageMain.setScene(scene);
      stageMain.setTitle("");
      stageMain.show();
    } catch (Exception e) {
      messageHandler.showAlert("Erro", "Erro", "Não foi possivel carregar a tela de principal", e.getMessage());
    }
  }
}