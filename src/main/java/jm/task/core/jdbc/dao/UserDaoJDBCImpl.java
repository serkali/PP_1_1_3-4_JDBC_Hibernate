package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final String tableName = "user";
    private final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (
                PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS "
                        + tableName + "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,name VARCHAR(35) NOT NULL ,"
                        + "lastname VARCHAR(35) NOT  NULL ,age MEDIUMINT NOT NULL) ")
        ) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось создать таблицу");
        }

    }

    public void dropUsersTable() {
        try (
                PreparedStatement statement = connection.prepareStatement("DROP TABLE  IF EXISTS " + tableName)) {
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось удалить таблицу");
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (
                PreparedStatement statement = connection.prepareStatement("INSERT INTO user (name ,lastname,age) values (?,?,?)")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось добавить пользователей");
        }
    }

    public void removeUserById(long id) {
        try (
                PreparedStatement statement = connection.prepareStatement("DELETE FROM" + tableName
                        + "WHERE id  values (?)")) {
            statement.setLong(1,id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось удалить пользователя");
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + tableName)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getString(2), resultSet.getString(3)
                        , resultSet.getByte(4));
                users.add(user);
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось получить пользователей");
        }
        return users;
    }

    public void cleanUsersTable() {
        try (
                PreparedStatement statement = connection.prepareStatement("DELETE FROM " + tableName)) {
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось очистить таблицу");
        }
    }

}
