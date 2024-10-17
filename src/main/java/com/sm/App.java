package com.sm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/sm/views/viewLogin.fxml"));
        Parent root = fxmlLoader.load();
        Scene login = new Scene(root);

        primaryStage.setTitle("Login");
        primaryStage.setScene(login);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}