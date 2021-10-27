package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getConnection(){
        Connection connection = null;
        final String DRIVER = "com.mysql.cj.jdbc.Driver";
        final String URL = "jdbc:mysql://localhost:3306/mydbtest?autoReconnect=true&useSSL=false&serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "root";

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        }catch (ClassNotFoundException | SQLException exception){
            System.out.println("Класс драйвера не обнаружен!");
        }
        return connection;
    }
}
