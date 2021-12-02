package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Util util = new Util();
    private Connection connection = util.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users (id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(45) NOT NULL, lastName VARCHAR(60) NOT NULL, age SMALLINT NOT NULL);");
             System.out.println("Создана новая таблица");
        } catch (SQLException throwables) {
            System.out.println("Ошибка при создании новой таблицы");
        }

    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate("DROP TABLE IF EXISTS Users");
            System.out.println("Таблица удалена");
        } catch (SQLException throwables) {
            System.out.println("Ошибка при удалении таблицы");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate("INSERT INTO Users (name, lastname, age) VALUES (?, ?, ?)");
            System.out.println("Пользователь с именем " + name + " добавлен в базу данных");
        } catch (SQLException throwables) {
            System.out.println("Ошибка при добавлении нового пользователя");
        }
    }

        public void removeUserById ( long id){
            try (Statement statement = connection.createStatement()){
                statement.executeUpdate("DELETE FROM Users WHERE id = ?");
            System.out.println("Удаление пользователя прошло успешно");
        } catch (SQLException throwables) {
            System.out.println("Ошибка при удалении пользователя");
        }
        }

        public List<User> getAllUsers () {
            List<User> userList = new ArrayList<>();

            try (Statement statement = connection.createStatement()){
                ResultSet resultSet = statement.executeQuery("SELECT name, lastname, age FROM Users");

                while(resultSet.next()) {
                    User user = new User();
                    user.setName(resultSet.getString("name"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setAge(resultSet.getByte("age"));
                    userList.add(user);
                }
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
            return userList;
        }

        public void cleanUsersTable () {

            try (Statement statement = connection.createStatement()){
                statement.executeUpdate("DELETE FROM Users");

                System.out.println("Таблица удалена");
            } catch (SQLException throwables) {
                System.out.println("Ошибка при очистке таблицы");
            }

        }

    }
