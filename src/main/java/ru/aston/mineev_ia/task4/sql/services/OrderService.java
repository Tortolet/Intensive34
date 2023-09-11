package ru.aston.mineev_ia.task4.sql.services;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import ru.aston.mineev_ia.task2.exceptions.ConstraintViolationException;
import ru.aston.mineev_ia.task2.exceptions.OrderDoesntExist;
import ru.aston.mineev_ia.task2.exceptions.UserDoesntExist;
import ru.aston.mineev_ia.task2.exceptions.ViolationCommunications;
import ru.aston.mineev_ia.task4.sql.dao.OrderDAO;
import ru.aston.mineev_ia.task4.sql.models.Order;
import ru.aston.mineev_ia.task4.sql.models.User;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderService implements OrderDAO<Order> {

    private static final String SQL_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\sql\\";

    private final H2DatabaseService h2DatabaseService;
    private final UserService userService;

    public OrderService(H2DatabaseService h2DatabaseService, UserService userService) {
        this.h2DatabaseService = h2DatabaseService;
        this.userService = userService;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = h2DatabaseService.getConnection()) {
            File query = new File(SQL_PATH + "find_all_orders.sql");
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(ExecuteSqlService.fromFile(query));
                while (resultSet.next()) {
                    User user = userService.findEntityById(resultSet.getInt("user_id"));
                    orders.add(new Order(resultSet.getInt("id"), resultSet.getString("item"), user));
                }
                return orders;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public Order findEntityById(int id) {
        try (Connection connection = h2DatabaseService.getConnection()) {
            File query = new File(SQL_PATH + "find_order_by_id.sql");
            try (PreparedStatement preparedStatement = connection.prepareStatement(ExecuteSqlService.fromFile(query))) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    User user = userService.findEntityById(resultSet.getInt("user_id"));
                    return new Order(resultSet.getInt("id"), resultSet.getString("item"), user);
                } else throw new UserDoesntExist("Заказа с ID - " + id + " не существует в базе данных");
            }
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = h2DatabaseService.getConnection()) {
            File queryDelete = new File(SQL_PATH + "delete_order_by_id.sql");
            try (PreparedStatement statement = connection.prepareStatement(ExecuteSqlService.fromFile(queryDelete))) {
                statement.setInt(1, id);
                int res = statement.executeUpdate();
                if (res == 1) {
                    return true;
                } else {
                    throw new OrderDoesntExist("Заказа с ID - " + id + " не существует в базе данных");
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
    public boolean create(Order t) {
        try (Connection connection = h2DatabaseService.getConnection()) {
            File queryCreate = new File(SQL_PATH + "create_order.sql");
            try (PreparedStatement statement = connection.prepareStatement(ExecuteSqlService.fromFile(queryCreate))) {
                statement.setString(1, t.getItem());
                statement.setInt(2, t.getUser().getId());

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
    public Order update(Order t) {
        try (Connection connection = h2DatabaseService.getConnection()) {
            File queryUpdate = new File(SQL_PATH + "update_order.sql");
            try (PreparedStatement statement = connection.prepareStatement(ExecuteSqlService.fromFile(queryUpdate))) {
                statement.setString(1, t.getItem());
                statement.setInt(2, t.getUser().getId());
                statement.setInt(3, t.getId());

                int resultSet = statement.executeUpdate();
                if (resultSet == 1) {
                    return new Order(t.getId(), t.getItem(), t.getUser());
                } else {
                    throw new OrderDoesntExist("Заказа с ID - " + t.getId() + " не существует в базе данных");
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
}
