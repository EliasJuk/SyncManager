package com.sm.controllers.colaboradores;

import java.net.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import com.sm.dao.sqlite.empresa.BUDAO;
import com.sm.dao.sqlite.empresa.SetorDAO;
import com.sm.dao.sqlite.pessoas.ColaboradorDAO;
import com.sm.models.Table;
import com.sm.models.empresa.BU;
import com.sm.models.empresa.Setor;
import com.sm.utils.callMessage;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

public class presencaController implements Initializable {
  private final ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
  private final callMessage messageHandler = new callMessage();
  LocalDate selectedDate = null;

  // COMPNENTES REFRENTE A MENUS DE SELEÇÃO
  @FXML
  private ComboBox<BU> buNameBox;
  @FXML
  private ComboBox<String> setorNameBox;
  @FXML
  private DatePicker dpDataSelect;
  @FXML
  private TextField tfNomeColaborador;
  @FXML
  private Button btnSalvar;

  @FXML
  void cbBUOnAction(ActionEvent event) {
    BU selectedBU = buNameBox.getSelectionModel().getSelectedItem();
    if (selectedBU != null) {
      carregarSetores(selectedBU.getId());
    }
  }

  @FXML
  void cbSetorOnAction(ActionEvent event) {

  }

  @FXML
  void dpSelectDataOnAction(ActionEvent event) {
    LocalDate selectedDate = dpDataSelect.getValue();
    if (selectedDate != null) {
      System.out.println("Data selecionada: " + selectedDate);
    } else {
      System.out.println("Nenhuma data selecionada.");
    }
  }

  @FXML
  void tfColaboradorNameOnAction(ActionEvent event) {

  }

  @FXML
  void btnSalvarOnAction(ActionEvent event) {
    if(selectedDate != null){

    }else{
      messageHandler.showAlert(Alert.AlertType.WARNING, "AVISO!", "SELECIONE UMA DATA", 
      "SELECIONE UMA DATA POR FAVOR");
    }
  }

  // COMPONENTES REFERENTE A TABELA
  @FXML
  TableView<Table> tableID;
  @FXML
  private TableColumn<Table, String> iMatriculaBox;
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
    //CARREGA O COMBOBOX DE BU
    carregarBUComboBox();

    // CARREGA A TABELA DE PRESENÇA
    iMatriculaBox.setCellValueFactory(new PropertyValueFactory<>("rMatricula"));
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
        new Table("200001","Airi", "Matsumoto", true, false, false),
        new Table("200002","Misaki", "Aoki", false, true, false),
        new Table("200003","Emi", "Oshida", false, false, true)));
  }

  private void carregarBUComboBox() {
    BUDAO buDAO = new BUDAO();
    try {
      buDAO.connect();
      List<BU> buList = buDAO.findAll();
      buNameBox.setItems(FXCollections.observableArrayList(buList));
      buDAO.close();
    } catch (Exception e) {
      messageHandler.showAlert("Erro", "Erro ao carregar a lista de BUs:", e.getMessage());
    }
  }

  private void carregarSetores(int buId) {
    SetorDAO setorDAO = new SetorDAO();
    try {
      setorDAO.connect();
      List<Setor> setores = setorDAO.findByBUId(buId);
      List<String> nomesSetores = setores.stream().map(Setor::getNome).collect(Collectors.toList());

      setorNameBox.setItems(FXCollections.observableArrayList(nomesSetores));
      setorDAO.close();
    } catch (Exception e) {
      messageHandler.showAlert("Erro", "Erro ao carregar a lista de Setores:", e.getMessage());
    }
  }
}