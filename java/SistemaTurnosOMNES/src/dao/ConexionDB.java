package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionDB {

    private static final String URL =
            "jdbc:mysql://localhost:3306/omnes_db";

    private static final String USER = "root";

    private static final String PASSWORD = "root123";

    public static Connection conectar() {

        try {

            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("==================================");
            System.out.println("   CONEXIÓN EXITOSA A MYSQL");
            System.out.println("==================================");

            return con;

        } catch (Exception e) {

            e.printStackTrace();

            return null;

        }

    }

}