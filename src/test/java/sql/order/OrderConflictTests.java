package sql.order;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import resolvers.OrderServiceParameterResolver;
import resolvers.UserServiceParameterResolver;
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

@ExtendWith({OrderServiceParameterResolver.class, UserServiceParameterResolver.class})
public class OrderConflictTests {

    private final OrderService orderService;
    private final UserService userService;

    public OrderConflictTests(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @BeforeAll
    static void init() {
        H2DatabaseService databaseService = new H2DatabaseService();
        try(Connection connection = databaseService.getConnection()) {
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
