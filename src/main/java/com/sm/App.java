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

  public static void main(String[] args) throws SQLException {    
    initTable();
    launch(args);
  }

  public static void initTable() throws SQLException{
    ComponenteDAO objComponenteDAO = new ComponenteDAO();
    CircuitoDAO objCircuitoDAO = new CircuitoDAO();
    BUDAO objBudao = new BUDAO();
    SetorDAO objSetorDAO = new SetorDAO();
    FuncaoDAO objFuncaoDAO = new FuncaoDAO();
    
    try {
      objBudao.connect();
      objBudao.createTable();
      objBudao.close();
      
      objSetorDAO.connect();
      objSetorDAO.createTable();
      objSetorDAO.close();

      objFuncaoDAO.connect();
      objFuncaoDAO.createTable();
      objFuncaoDAO.close();

      objComponenteDAO.connect();
      objComponenteDAO.createTable();
      objComponenteDAO.close();

      objCircuitoDAO.connect();
      objCircuitoDAO.createTable();
      objCircuitoDAO.close();

      objFuncaoDAO.connect();
      objFuncaoDAO.createTable();
      objFuncaoDAO.close();
    } catch (Exception e) {
      //System.err.println("Erro: " + e.getMessage());
    }
  }
}