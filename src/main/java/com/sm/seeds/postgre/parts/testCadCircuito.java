package com.sm.seeds.postgre.parts;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.sm.dao.postgre.parts.CircuitoDAO;
import com.sm.dao.postgre.parts.ComponenteDAO;
import com.sm.models.parts.Circuito;
import com.sm.models.parts.Componente;

public class testCadCircuito {
	public static void main(String[] args) {
		// Criar os componentes
		Componente componente1 = new Componente("Resistor", "CTF0051", 0.50, "Resistor de 10 Ohms");
		Componente componente2 = new Componente("Capacitor", "CTF0010", 1.20, "Capacitor de 100uF");

		// Definir as quantidades para os componentes
		Map<Componente, Integer> componentesQuantidade = new HashMap<>();
		componentesQuantidade.put(componente1, 10); // 10 resistores
		componentesQuantidade.put(componente2, 5); // 5 capacitores

		// Criar o circuito com os componentes e suas quantidades
		//Circuito circuito = new Circuito("Circuito Eletrônico", "CTF10041", componentesQuantidade);
		Circuito circuito = new Circuito("Circuito Eletrônico", "CTF10041", componentesQuantidade, LocalDateTime.now());

		// Instanciar os DAOs
		CircuitoDAO circuitoDAO = new CircuitoDAO();
		ComponenteDAO componenteDAO = new ComponenteDAO();

		try {
			// Conectar ao banco
			componenteDAO.connect();
			circuitoDAO.connect();

			// Criar tabelas, se necessário
			componenteDAO.createTable();
			circuitoDAO.createTable();
			circuitoDAO.createTableCircuitoComponente();

			// Criar os componentes no banco de dados
			componenteDAO.createComponente(componente1);
			componenteDAO.createComponente(componente2);

			// Criar o circuito no banco de dados
			circuitoDAO.createCircuito(circuito);

			// Fechar as conexões
			componenteDAO.close();
			circuitoDAO.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
