package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    private static final UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        userService.createUsersTable();

        User user1 = new User("Виктор", "Куркин", (byte) 30);
        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        User user2 = new User("Сергей", "Сорокопудов", (byte) 30);
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        User user3 = new User("Александр", "Зайцев", (byte) 45);
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        User user4 = new User("Кирилл", "Коломок", (byte) 72);
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

        System.out.println(userService.getAllUsers().toString());

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
