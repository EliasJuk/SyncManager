package com.sm;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Routes {

    public static void Login(Class<?> clazz, Stage primaryStage) throws Exception{
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(clazz.getResource("/com/sm/views/viewLogin.fxml"));
            Parent root = fxmlLoader.load();
            Scene login = new Scene(root);
    
            primaryStage.setTitle("Login");
            primaryStage.setScene(login);
            primaryStage.show();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }    
}
