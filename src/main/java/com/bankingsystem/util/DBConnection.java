package com.bankingsystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static final String user = "root";
    static final String password = "Sanjay";
    static final String url = "jdbc:mysql://localhost:3306/banking_system";
  public static Connection getConnection() throws SQLException {
       return DriverManager.getConnection(url,user,password);
   }
}
