package com.sm;


import com.sm.utils.callMessage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Routes {
  private final static callMessage messageHandler = new callMessage();

  // CARREGA A VIEW INICIAL DE LOGIN
  @SuppressWarnings("exports")
  public static void Login(Class<?> clazz, Stage primaryStage) throws Exception {
    showScenes("/com/sm/views/viewLogin.fxml", "Login", primaryStage);
  }

  // CARREGA A VIEW PRINCIPAL DE MENUS
  public static void mainView(Class<?> clazz) throws Exception {
    Stage stageMain = new Stage();
    showScenes("/com/sm/views/viewMain.fxml", "Principal", stageMain);
  }

  // CARREGA A VIEW DE PRESENÇA
  public static void showViewPresenca(Class<?> clazz) {
    Stage stageMain = new Stage();
    showScenes("/com/sm/views/Presenca/viewPresenca.fxml", "Presença", stageMain);
  }

  // CARREGA A VIEW DE BU
  public static void showViewBU(Class<?> clazz) {
    Stage stageMain = new Stage();
    showScenes("/com/sm/views/empresa/viewBU.fxml", "BU", stageMain);
  }

  // CARREGA A VIEW DE SETOR
  public static void showViewSetor(Class<?> clazz) {
    Stage stageMain = new Stage();
    showScenes("/com/sm/views/empresa/viewSetor.fxml", "Setor", stageMain);
  }

  // Método utilitário para carregar e exibir uma cena
  @SuppressWarnings("exports")
  public static void showScenes(String fxmlPath, String title, Stage stage) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(Routes.class.getResource(fxmlPath));
      Parent root = fxmlLoader.load();
      Scene scene = new Scene(root);

      stage.setTitle(title);
      stage.setScene(scene);
      stage.show();
    } catch (Exception e) {
      messageHandler.showAlert("Erro", "Erro", "Não foi possível carregar a tela", e.getMessage());
      // APENAS PARA DEBUGAR
      //e.printStackTrace();
    }
  }
}