package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String createUsersTable = "CREATE TABLE IF NOT EXISTS Users (id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(45) NOT NULL, lastName VARCHAR(60) NOT NULL, age SMALLINT NOT NULL);";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(createUsersTable)) {
            preparedStatement.executeUpdate();
            System.out.println("Создана новая таблица");
        } catch (SQLException throwables) {
            System.out.println("Ошибка при создании новой таблицы");
        }

    }

    public void dropUsersTable() {
        String dropUsersTable = "DROP TABLE IF EXISTS Users";

        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(dropUsersTable)) {
            preparedStatement.executeUpdate();
            System.out.println("Таблица удалена");
        } catch (SQLException throwables) {
            System.out.println("Ошибка при удалении таблицы");
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUser = "INSERT INTO Users (name, lastname, age) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(saveUser)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("Пользователь с именем " + name + " добавлен в базу данных");

        } catch (SQLException throwables) {
            System.out.println("Ошибка при добавлении нового пользователя");
        }
    }

        public void removeUserById ( long id){
        String removeUserById = "DELETE FROM Users WHERE id = ?";
        try(PreparedStatement preparedStatement = Util.getConnection().prepareStatement(removeUserById)){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Удаление пользователя прошло успешно");
        } catch (SQLException throwables) {
            System.out.println("Ошибка при удалении пользователя");
        }

        }

        public List<User> getAllUsers () {
            List<User> userList = new ArrayList<>();
            String getAllUsers = "SELECT name, lastname, age FROM Users";
            try(PreparedStatement preparedStatement = Util.getConnection().prepareStatement(getAllUsers)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()) {
                    User user = new User();
                    user.setName(resultSet.getString("name"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setAge(resultSet.getByte("age"));
                    userList.add(user);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return userList;
        }

        public void cleanUsersTable () {
        String cleanUsersTable = "DELETE FROM Users";
            try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(cleanUsersTable)) {
                preparedStatement.executeUpdate();
                System.out.println("Таблица удалена");
            } catch (SQLException throwables) {
                System.out.println("Ошибка при очистке таблицы");
            }

        }
    }
