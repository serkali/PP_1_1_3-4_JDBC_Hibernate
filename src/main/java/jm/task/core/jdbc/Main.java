package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        List<User> users = new ArrayList<>();
        for (User user : users) {
            users.add(new User("Garry", "Potter", (byte) 20));
            users.add(new User("Legolas", "Elf", (byte) 2000));
            users.add(new User("Viktor", "Vega", (byte) 35));
            users.add(new User("Tony", "Stark", (byte) 35));
            userService.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.printf("User с именем – %s добавлен в базу данных\n", user.getName());
            System.out.println(userService.getAllUsers());
        }
        userService.removeUserById(1);
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }

}
