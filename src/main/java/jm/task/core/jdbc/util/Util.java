package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соединения с БД
    private static String jdbcURL = "jdbc:mysql://localhost:3306/jm";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "root";

    public Connection getConnection() {
        Connection connection = null;
        try {
            //  Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            System.out.println("Соединение установлено");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Соединение закрыто");
        }
        return connection;
    }
}
