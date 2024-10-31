package com.sm.controllers.colaboradores;

import java.net.*;
import java.util.*;

import com.sm.utils.Table;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class presencaController implements Initializable {
  @FXML
  TableView<Table> tableID;
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
    iLastName.setCellValueFactory(new PropertyValueFactory<Table, String>("rLastName"));

    iPresenteBox.setCellValueFactory(new Callback<CellDataFeatures<Table, Boolean>, ObservableValue<Boolean>>() {
      @Override
      public ObservableValue<Boolean> call(CellDataFeatures<Table, Boolean> cellData) {
        return cellData.getValue().presenteCheckedProperty(true);
      }
    });
    iPresenteBox.setCellFactory(CheckBoxTableCell.forTableColumn(iPresenteBox));

    iFaltaBox.setCellValueFactory(new Callback<CellDataFeatures<Table, Boolean>, ObservableValue<Boolean>>() {
      @Override
      public ObservableValue<Boolean> call(CellDataFeatures<Table, Boolean> cellData) {
        return cellData.getValue().faltaCheckedProperty(true);
      }
    });
    iFaltaBox.setCellFactory(CheckBoxTableCell.forTableColumn(iFaltaBox));

    iAtestadoBox.setCellValueFactory(new Callback<CellDataFeatures<Table, Boolean>, ObservableValue<Boolean>>() {
      @Override
      public ObservableValue<Boolean> call(CellDataFeatures<Table, Boolean> cellData) {
        return cellData.getValue().atestadoCheckedProperty(true);
      }
    });
    iAtestadoBox.setCellFactory(CheckBoxTableCell.forTableColumn(iAtestadoBox));

    // Lógica de atualização para seleção exclusiva das checkboxes
    iPresenteBox.setCellFactory(column -> new CheckBoxTableCell<Table, Boolean>() {
      private final CheckBox checkBox = new CheckBox();

      @Override
      public void updateItem(Boolean item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
          setGraphic(null);
        } else {
          checkBox.setSelected(item);
          checkBox.setOnAction(e -> {
            if (checkBox.isSelected()) {
              getTableView().getItems().forEach(row -> {
                row.presenteCheckedProperty(false).set(false);
                row.faltaCheckedProperty(false).set(false);
                row.atestadoCheckedProperty(false).set(false);
              });
              getTableView().getItems().get(getIndex()).presenteCheckedProperty(true).set(true);
            }
          });
          setGraphic(checkBox);
        }
      }
    });

    iFaltaBox.setCellFactory(column -> new CheckBoxTableCell<Table, Boolean>() {
      private final CheckBox checkBox = new CheckBox();

      @Override
      public void updateItem(Boolean item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
          setGraphic(null);
        } else {
          checkBox.setSelected(item);
          checkBox.setOnAction(e -> {
            if (checkBox.isSelected()) {
              getTableView().getItems().forEach(row -> {
                row.presenteCheckedProperty(false).set(false);
                row.faltaCheckedProperty(false).set(false);
                row.atestadoCheckedProperty(false).set(false);
              });
              getTableView().getItems().get(getIndex()).faltaCheckedProperty(true).set(true);
            }
          });
          setGraphic(checkBox);
        }
      }
    });

    iAtestadoBox.setCellFactory(column -> new CheckBoxTableCell<Table, Boolean>() {
      private final CheckBox checkBox = new CheckBox();

      @Override
      public void updateItem(Boolean item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
          setGraphic(null);
        } else {
          checkBox.setSelected(item);
          checkBox.setOnAction(e -> {
            if (checkBox.isSelected()) {
              getTableView().getItems().forEach(row -> {
                row.presenteCheckedProperty(false).set(false);
                row.faltaCheckedProperty(false).set(false);
                row.atestadoCheckedProperty(false).set(false);
              });
              getTableView().getItems().get(getIndex()).atestadoCheckedProperty(true).set(true);
            }
          });
          setGraphic(checkBox);
        }
      }
    });

    // Populando a tabela
    tableID.setItems(FXCollections.observableArrayList(
        new Table("Airi", "Matsumoto", true, false, false),
        new Table("Misaki", "Aoki", false, true, false),
        new Table("Emi", "Oshida", false, false, true)));
  }
}