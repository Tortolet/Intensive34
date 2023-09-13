package sql.order;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.aston.mineev_ia.task4.sql.models.Order;
import ru.aston.mineev_ia.task4.sql.models.User;
import ru.aston.mineev_ia.task4.sql.services.OrderService;
import ru.aston.mineev_ia.task4.sql.services.UserService;

import java.util.ArrayList;
import java.util.List;

public class OrderMockTest {
    @Mock
    private OrderService mockOrder;

    @Mock
    private UserService mockUser;

    @BeforeEach
    void initEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAllOrders() {
        List<Order> orders = new ArrayList<>();
        Mockito.when(mockUser.findEntityById(2)).thenReturn(new User(1, "Иван", "Андреевич", "Минеев", "89126144959", "ivanmineev52@gmail.com"));
        User user = mockUser.findEntityById(2);

        orders.add(new Order(1, "Test-item2", user));
        Mockito.when(mockOrder.findAll()).thenReturn(orders);
        Assertions.assertArrayEquals(orders.toArray(), mockOrder.findAll().toArray());
    }

    @Test
    void testFindOrderById() {
        Mockito.when(mockUser.findEntityById(2)).thenReturn(new User(1, "Иван", "Андреевич", "Минеев", "89126144959", "ivanmineev52@gmail.com"));
        User user = mockUser.findEntityById(2);

        Order orderExpected = new Order(1, "Test-item2", user);
        Mockito.when(mockOrder.findEntityById(3)).thenReturn(orderExpected);
        Assertions.assertEquals(orderExpected, mockOrder.findEntityById(3));
    }

    @Test
    void testDeleteOrderById() {
        Mockito.when(mockOrder.delete(1281)).thenReturn(true);
        Assertions.assertTrue(mockOrder.delete(1281));
    }

    @Test
    void testCreateOrder() {
        Mockito.when(mockUser.findEntityById(2)).thenReturn(new User(1, "Иван", "Андреевич", "Минеев", "89126144959", "ivanmineev52@gmail.com"));
        User user = mockUser.findEntityById(2);

        Order order = new Order();
        order.setItem("Test-item");
        order.setUser(user);
        Mockito.when(mockOrder.create(order)).thenReturn(true);
        Assertions.assertTrue(mockOrder.create(order));
    }

    @Test
    void testUpdateOrder() {
        Mockito.when(mockUser.findEntityById(2)).thenReturn(new User(1, "Иван", "Андреевич", "Минеев", "89126144959", "ivanmineev52@gmail.com"));
        User user = mockUser.findEntityById(2);

        Order orderExpected = new Order(1, "Test-item1337", user);
        Mockito.when(mockOrder.update(orderExpected)).thenReturn(orderExpected);
        Assertions.assertEquals(orderExpected, mockOrder.update(orderExpected));
    }
}
