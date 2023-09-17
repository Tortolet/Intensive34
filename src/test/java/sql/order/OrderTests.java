package sql.order;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.aston.mineev_ia.task4.sql.models.Order;
import ru.aston.mineev_ia.task4.sql.models.User;
import ru.aston.mineev_ia.task4.sql.services.H2DatabaseService;
import ru.aston.mineev_ia.task4.sql.services.OrderService;
import ru.aston.mineev_ia.task4.sql.services.UserService;

import java.util.ArrayList;
import java.util.List;

public class OrderTests {

    private static final H2DatabaseService H2_DB_SERVICE = new H2DatabaseService();
    private final UserService userService = new UserService(H2_DB_SERVICE);
    private final OrderService orderService = new OrderService(H2_DB_SERVICE, userService);

    @BeforeAll
    static void init() {
        H2_DB_SERVICE.createTables();
    }

    @Test
    void testAllOrders() {
        List<Order> orders = new ArrayList<>();
        User user = userService.findEntityById(2);

        orders.add(new Order(1, "Test-item2", user));
        Assertions.assertArrayEquals(orders.toArray(), orderService.findAll().toArray());
    }

    @Test
    void testFindOrderById() {
        User user = userService.findEntityById(2);

        Order orderExpected = new Order(1, "Test-item2", user);
        Assertions.assertEquals(orderExpected, orderService.findEntityById(1));
    }

}
