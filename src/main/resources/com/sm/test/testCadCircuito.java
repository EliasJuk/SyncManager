package com.sm.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sm.dao.parts.*;
import com.sm.models.parts.*;

public class testCadCircuito {
    public static void main(String[] args) {
    Componente componente1 = new Componente("Resistor", "CTF0051", 0.50, "Resistor de 10 Ohms");
    Componente componente2 = new Componente("Capacitor", "CTF0010", 1.20, "Capacitor de 100uF");

    List<Componente> componentes = new ArrayList<>();
    componentes.add(componente1);
    componentes.add(componente2);

    Circuito circuito = new Circuito("Circuito Eletrônico", "CTF10041", componentes);

    CircuitoDAO circuitoDAO = new CircuitoDAO();
    try {
      circuitoDAO.connect();
      circuitoDAO.createTable();
      circuitoDAO.createCircuito(circuito);
      circuitoDAO.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
