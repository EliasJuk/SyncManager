package com.sm.database;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DatabaseConnectionTest {

    private Connection connection;

    @BeforeEach
    public void setUp() throws SQLException {
        connection = DatabaseConnection.getConnection();
    }

    @Test
    public void testConnectionNotNull() {
        assertNotNull(connection, "A conexão não deve ser nula.");
    }

    @Test
    public void testConnectionIsOpen() throws SQLException {
        assertFalse(connection.isClosed(), "A conexão deve estar aberta.");
    }

    @AfterEach
    public void tearDown() throws SQLException {
        DatabaseConnection.closeConnection();
    }
}