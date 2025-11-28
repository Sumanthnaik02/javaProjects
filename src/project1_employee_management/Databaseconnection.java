package project1_employee_management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Databaseconnection {
        private static final String URL="jdbc:mysql://127.0.0.1:3306/employeedb";
        private static final String USER="root";
        private static final String PASSWORD="sumanthnaik@187";
    public static Connection connect(){
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("connected to MySQL Database!");
            return connection;
        } catch (SQLException e) {
            System.out.println("Database connection Failed!");
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args){
        connect();
    }
}