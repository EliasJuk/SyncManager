package com.sm.controllers.colaboradores;

import java.net.*;
import java.util.*;

import com.sm.utils.Table;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

public class presencaController implements Initializable {

  // COMPNENTES REFRENTE A MENUS DE SELEÇÃO
  @FXML
  private ComboBox<?> buNameBox;

  @FXML
  private DatePicker dpDataSelect;

  @FXML
  private ComboBox<?> setorNameBox;

  @FXML
  private TextField tfNomeColaborador;

  @FXML
  void cbBUOnAction(ActionEvent event) {

  }

  @FXML
  void cbSetorOnAction(ActionEvent event) {

  }

  @FXML
  void dpSelectDataOnAction(ActionEvent event) {

  }

  @FXML
  void tfColaboradorNameOnAction(ActionEvent event) {

  }

  // COMPONENTES REFERENTE A TABELA
  @FXML
  TableView<Table> tableID;
  @FXML
  private TableColumn<?, ?> iMatriculaBox;
  @FXML
  TableColumn<Table, String> iFirstName;
  @FXML
  TableColumn<Table, String> iLastName;
  @FXML
  TableColumn<Table, Boolean> iPresenteBox;
  @FXML
  TableColumn<Table, Boolean> iFaltaBox;
  @FXML
  TableColumn<Table, Boolean> iAtestadoBox;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    iFirstName.setCellValueFactory(new PropertyValueFactory<>("rFirstName"));
    iLastName.setCellValueFactory(new PropertyValueFactory<>("rLastName"));

    iPresenteBox.setCellValueFactory(cellData -> cellData.getValue().presenteCheckedProperty());
    iPresenteBox.setCellFactory(CheckBoxTableCell.forTableColumn(iPresenteBox));

    iFaltaBox.setCellValueFactory(cellData -> cellData.getValue().faltaCheckedProperty());
    iFaltaBox.setCellFactory(CheckBoxTableCell.forTableColumn(iFaltaBox));

    iAtestadoBox.setCellValueFactory(cellData -> cellData.getValue().atestadoCheckedProperty());
    iAtestadoBox.setCellFactory(CheckBoxTableCell.forTableColumn(iAtestadoBox));

    // Populando a tabela com os dados de teste
    tableID.setItems(FXCollections.observableArrayList(
      new Table("Airi", "Matsumoto", true, false, false),
      new Table("Misaki", "Aoki", false, true, false),
      new Table("Emi", "Oshida", false, false, true)));
  }
}