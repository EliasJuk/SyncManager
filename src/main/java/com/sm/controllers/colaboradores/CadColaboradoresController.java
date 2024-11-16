package com.sm.controllers.colaboradores;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import com.sm.dao.sqlite.empresa.*;
import com.sm.dao.sqlite.pessoas.*;
import com.sm.models.pessoas.Colaborador;
import com.sm.utils.callMessage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;


public class CadColaboradoresController {
	private final static callMessage messageHandler = new callMessage();

	@FXML
	private Pane pane;

	@FXML
	private ChoiceBox<String> colaboradorCadBU;

	@FXML
	private TextField colaboradorCadCEP;

	@FXML
	private TextField colaboradorCadEndereco;

	@FXML
	private ChoiceBox<String> colaboradorCadFuncao;

	@FXML
	private TextField colaboradorCadMatricula;

	@FXML
	private TextField colaboradorCadNome;

	@FXML
	private DatePicker colaboradorCadNasc;

	@FXML
	private ChoiceBox<String> colaboradorCadSetor;

	@FXML
	private TextField colaboradorCadTel;

	@FXML
	private TextField colaboradorCadTelR;

	@FXML
	private Button btnColaboradorCadSalvar;

	@FXML
	private Button btnColaboradorCadCancelar;

	public void initialize() {
    Button button = new Button("Raised Button");
    button.getStyleClass().add("raised-button");

		//VALIDAÇÃO DE DATA DE NAS CIMENTO
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		colaboradorCadNasc.setConverter(new StringConverter<LocalDate>() {
			@Override
			public String toString(LocalDate date) {
				if (date != null) {
					return formatter.format(date);
				} else {
					return "";
				}
	}

		@Override
		public LocalDate fromString(String string) {
			// Tentando parsear a data em dois formatos
			String[] formats = {"ddMMyyyy", "dd/MM/yyyy"};

			for (String format : formats) {
				try {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
					return LocalDate.parse(string, formatter);
				} catch (DateTimeParseException e) {
					// Ignorar o erro e tentar o próximo formato
				}
			}
			return null; // Se nenhum formato funcionar, retorna null
		}
	});

		carregarFuncoes();
	}


	@FXML
	void btnCadColaborador(ActionEvent event) {
		String nome = colaboradorCadNome.getText();
		String dataNasc = colaboradorCadNasc.getValue() != null ? colaboradorCadNasc.getValue().toString() : "";
		String matricula = colaboradorCadMatricula.getText();
		String tel = colaboradorCadTel.getText();
		String telR = colaboradorCadTelR.getText();
		String endereco = colaboradorCadEndereco.getText();
		String cep = colaboradorCadCEP.getText();
		String bu = colaboradorCadBU.getValue() != null ? colaboradorCadBU.getValue() : "não selecionada";
		String setor = colaboradorCadSetor.getValue() != null ? colaboradorCadSetor.getValue() : "não selecionada";
		String funcao = colaboradorCadFuncao.getValue() != null ? colaboradorCadFuncao.getValue() : "não selecionada";

		// CADASTRA OS COLABORADORES
		cadastrarColaborador(nome, dataNasc, matricula, tel, telR, endereco, cep, bu, setor, funcao);
	}

	protected static void cadastrarColaborador(String nome, String dataNasc, String matricula, String tel, String telR,
				String endereco, String cep, String bu, String setor,  String funcao){

		ColaboradorDAO dao = new ColaboradorDAO();
		try {
			dao.connect();
			dao.createTable();
			Colaborador colaborador = new Colaborador("Colaborador 1");
			colaborador.setNome(nome);
			colaborador.setDataNascimento(dataNasc);
			colaborador.setMatricula(matricula);
			colaborador.setTelefone(tel);
			colaborador.setTelefoneContato(telR);
			colaborador.setEndereco(endereco);
			colaborador.setCep(cep);
			colaborador.setBu(bu);
			colaborador.setSetor(setor);
			colaborador.setFuncao(funcao);

			dao.createColaborador(colaborador);
			dao.close();
		} catch (SQLException e){
			messageHandler.showAlert(Alert.AlertType.ERROR, "Erro", "Falha ao Caddastrar!",
			"Ocorreu um erro ao tentar cadastrar os dados." + e.getMessage());
		}
	}

	private void carregarFuncoes() {
		FuncaoDAO funcaoDAO = new FuncaoDAO();
		try {
			funcaoDAO.connect();
			List<String> funcoes = funcaoDAO.getAllFuncoes();

			colaboradorCadFuncao.getItems().addAll(funcoes);
			funcaoDAO.close();
		} catch (SQLException e) {
			messageHandler.showAlert(Alert.AlertType.ERROR, "Erro", "Falha ao carregar funções!",
			"Ocorreu um erro ao tentar carregar as funções do banco de dados." + e.getMessage());
		}
	}
}