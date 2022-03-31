package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соединения с БД
    private static final SessionFactory sessionFactory = BuildSessionFactory();
    private static String jdbcURL = "jdbc:mysql://localhost:3306/jm";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "root";

    public static SessionFactory BuildSessionFactory() {
        SessionFactory sessionFactory = null;
        try {
            Configuration configuration = new Configuration()
                    .setProperty("connection.driver_class", "com.mysql.jdbc.Driver")
                    .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/jm?serverTimezone=UTC&useSSL=false")
                    .setProperty("hibernate.connection.username", "root")
                    .setProperty("hibernate.connection.password", "root")
                    .addAnnotatedClass(User.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Исключение!" + e);
        }
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }

    public static Connection getConnection() {
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
