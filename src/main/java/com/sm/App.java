package com.sm;

import javafx.application.Application;
import javafx.stage.Stage;


public class App extends Application {    
    @Override
    @SuppressWarnings("exports")
    public void start(Stage primaryStage) throws Exception{
        Routes.Login(getClass(), primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}