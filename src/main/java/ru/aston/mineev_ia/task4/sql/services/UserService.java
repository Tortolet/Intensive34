package ru.aston.mineev_ia.task4.sql.services;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import ru.aston.mineev_ia.task2.exceptions.ConstraintViolationException;
import ru.aston.mineev_ia.task2.exceptions.UserDoesntExist;
import ru.aston.mineev_ia.task2.exceptions.ViolationCommunications;
import ru.aston.mineev_ia.task4.sql.dao.UserDAO;
import ru.aston.mineev_ia.task4.sql.models.User;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService implements UserDAO<User> {

    private final H2DatabaseService h2DatabaseService;

    public UserService(H2DatabaseService h2DatabaseService) {
        this.h2DatabaseService = h2DatabaseService;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = h2DatabaseService.getConnection()) {
            File query = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\sql\\find_all_users.sql");
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(ExecuteSqlService.fromFile(query));
                while (resultSet.next()) {
                    users.add(new User(resultSet.getInt("id"),
                            resultSet.getString("firstName"),
                            resultSet.getString("secondName"),
                            resultSet.getString("lastName"),
                            resultSet.getString("phone"),
                            resultSet.getString("email")));
                }
                return users;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findEntityById(int id) {
        try (Connection connection = h2DatabaseService.getConnection()) {
            File query = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\sql\\find_user_by_id.sql");
            try (PreparedStatement preparedStatement = connection.prepareStatement(ExecuteSqlService.fromFile(query))) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    return new User(resultSet.getInt("id"),
                            resultSet.getString("firstName"),
                            resultSet.getString("secondName"),
                            resultSet.getString("lastName"),
                            resultSet.getString("phone"),
                            resultSet.getString("email"));
                } else throw new UserDoesntExist("Пользователя с ID - " + id + " не существует в базе данных");
            }
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(int id){
        try (Connection connection = h2DatabaseService.getConnection()) {
            File queryDelete = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\sql\\delete_user_by_id.sql");
            try (PreparedStatement statement = connection.prepareStatement(ExecuteSqlService.fromFile(queryDelete))) {
                statement.setInt(1, id);
                int res = statement.executeUpdate();
                if (res == 1) {
                    return true;
                } else {
                    throw new UserDoesntExist("Пользователя с ID - " + id + " не существует в базе данных");
                }
            } catch (JdbcSQLIntegrityConstraintViolationException e) {
                throw new ViolationCommunications("Нельзя удалить запись, которая связана с другой таблицей");
            }
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean create(User t) {
        try (Connection connection = h2DatabaseService.getConnection()) {
            File queryCreate = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\sql\\create_user.sql");
            try (PreparedStatement statement = connection.prepareStatement(ExecuteSqlService.fromFile(queryCreate))) {
                statement.setString(1, t.getFirstName());
                statement.setString(2, t.getSecondName());
                statement.setString(3, t.getLastName());
                statement.setString(4, t.getPhone());
                statement.setString(5, t.getEmail());

                int res = statement.executeUpdate();
                if (res == 1) {
                    return true;
                } else {
                    throw new RuntimeException("Ошибка");
                }
            }
            catch (JdbcSQLIntegrityConstraintViolationException e) {
                throw new ConstraintViolationException("Нарушение уникального индекса или первичного ключа");
            }
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User update(User t) {
        try (Connection connection = h2DatabaseService.getConnection()) {
            File queryUpdate = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\sql\\update_user.sql");
            try (PreparedStatement statement = connection.prepareStatement(ExecuteSqlService.fromFile(queryUpdate))) {
                statement.setString(1, t.getFirstName());
                statement.setString(2, t.getSecondName());
                statement.setString(3, t.getLastName());
                statement.setString(4, t.getPhone());
                statement.setString(5, t.getEmail());
                statement.setInt(6, t.getId());

                int resultSet = statement.executeUpdate();
                if (resultSet == 1) {
                    return new User(t.getId(), t.getFirstName(), t.getSecondName(), t.getLastName(), t.getPhone(), t.getEmail());
                } else {
                    throw new UserDoesntExist("Пользователя с ID - " + t.getId() + " не существует в базе данных");
                }
            }
            catch (JdbcSQLIntegrityConstraintViolationException e) {
                throw new ConstraintViolationException("Нарушение уникального индекса или первичного ключа");
            }
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> innerJoinWithOrders() {
        List<String> join = new ArrayList<>();
        try(Connection connection = h2DatabaseService.getConnection()) {
            File queryJoin = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\sql\\inner_join.sql");
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(ExecuteSqlService.fromFile(queryJoin));
                while (resultSet.next()) {
                    join.add("First Name: " + resultSet.getString("firstName") + ". Last Name: " + resultSet.getString("lastName") + ". Item: " + resultSet.getString("item"));
                }
            }
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return join;
    }
}
