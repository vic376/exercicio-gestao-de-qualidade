package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static String URL = "jdbc:mysql://localhost:3307/simulado?useSSL=false&serverTimezone=UTC";

    private static String USER = "root";

    private static String PASSWORD = "vi123";
    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
}
