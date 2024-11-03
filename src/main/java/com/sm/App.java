package com.sm;

import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.SQLException;

import com.sm.dao.postgre.parts.*;
import com.sm.dao.sqlite.empresa.*;

public class App extends Application {
  
  @Override
  @SuppressWarnings("exports")
  public void start(Stage primaryStage) throws Exception {
    Routes.Login(getClass(), primaryStage);
  }

  public static void main(String[] args) {
    launch(args);
  }

  public static void initTable() throws SQLException{
    ComponenteDAO objComponenteDAO = new ComponenteDAO();
    CircuitoDAO objCircuitoDAO = new CircuitoDAO();
    BUDAO objBudao = new BUDAO();
    SetorDAO objSetorDAO = new SetorDAO();
    FuncaoDAO objFuncaoDAO = new FuncaoDAO();

    objComponenteDAO.createTable();
    objCircuitoDAO.createTable();
    objFuncaoDAO.createTable();
    objBudao.createTable();
    objSetorDAO.createTable();
    objFuncaoDAO.createTable();
  }
}