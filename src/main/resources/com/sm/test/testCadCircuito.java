package com.sm.test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sm.dao.parts.*;
import com.sm.models.parts.*;

public class testCadCircuito {
    public static void main(String[] args) {
        // Criar os componentes
        Componente componente1 = new Componente("Resistor", "CTF0051", 0.50, "Resistor de 10 Ohms");
        Componente componente2 = new Componente("Capacitor", "CTF0010", 1.20, "Capacitor de 100uF");

        // Definir as quantidades para os componentes
        Map<Componente, Integer> componentesQuantidade = new HashMap<>();
        componentesQuantidade.put(componente1, 10); // 10 resistores
        componentesQuantidade.put(componente2, 5);  // 5 capacitores

        // Criar o circuito com os componentes e suas quantidades
        Circuito circuito = new Circuito("Circuito Eletrônico", "CTF10041", componentesQuantidade);

        // Instanciar o DAO
        CircuitoDAO circuitoDAO = new CircuitoDAO();
        try {
            // Conectar ao banco e criar tabelas
            circuitoDAO.connect();
            circuitoDAO.createTable();
            circuitoDAO.createTableCircuitoComponente();

            // Criar o circuito no banco de dados
            circuitoDAO.createCircuito(circuito);

            // Fechar a conexão
            circuitoDAO.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}