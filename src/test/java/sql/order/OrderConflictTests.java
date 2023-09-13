package sql.order;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.aston.mineev_ia.task4.sql.models.Order;
import ru.aston.mineev_ia.task4.sql.models.User;
import ru.aston.mineev_ia.task4.sql.services.ExecuteSqlService;
import ru.aston.mineev_ia.task4.sql.services.H2DatabaseService;
import ru.aston.mineev_ia.task4.sql.services.OrderService;
import ru.aston.mineev_ia.task4.sql.services.UserService;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderConflictTests {

    private static final H2DatabaseService H2_DB_SERVICE = new H2DatabaseService();
    private final UserService userService = new UserService(H2_DB_SERVICE);
    private final OrderService orderService = new OrderService(H2_DB_SERVICE, userService);

    @BeforeAll
    static void init() {
        try(Connection connection = H2_DB_SERVICE.getConnection()) {
            File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\sql\\add_new_tables.sql");

            // создание
            try (Statement statement = connection.createStatement()) {
                statement.execute(ExecuteSqlService.fromFile(file));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDeleteOrderById() {
        Assertions.assertTrue(orderService.delete(1));
    }

    @Test
    void testCreateOrder() {
        User user = userService.findEntityById(1);

        Order order = new Order();
        order.setItem("Test-item");
        order.setUser(user);

        Assertions.assertTrue(orderService.create(order));
    }

    @Test
    void testUpdateOrder() {
        User user = userService.findEntityById(1);
        Order orderExpected = new Order(1, "Test-item1337", user);
        Assertions.assertEquals(orderExpected, orderService.update(orderExpected));
    }

}
