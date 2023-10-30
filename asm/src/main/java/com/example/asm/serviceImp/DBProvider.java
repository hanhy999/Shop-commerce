package com.example.asm.serviceImp;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBProvider {
    public static Connection getConnection() {
        String dbURL = "jdbc:mysql://localhost:3306/db_java5";
        String userName = "root";
        String password = "";

        Connection conn = null;
        try {
            //call driver
//            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, userName, password);
            System.out.println("connect db sucsess");

        } catch (Exception e) {
            System.out.println("connect db failed");
            e.printStackTrace();
        }
        return conn;

    }
}
