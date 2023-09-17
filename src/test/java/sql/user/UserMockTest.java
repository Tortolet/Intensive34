package sql.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.aston.mineev_ia.task2.exceptions.ConstraintViolationException;
import ru.aston.mineev_ia.task4.sql.models.User;
import ru.aston.mineev_ia.task4.sql.services.UserService;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserMockTest {
    @Mock
    private UserService mock;

    @Test
    void testMockFindAll() {

        List<User> users = new ArrayList<>();
        users.add(new User(1, "Иван", "Андреевич", "Минеев", "89126144959", "ivanmineev52@gmail.com"));
        users.add(new User(2, "Артем", "Васильевич", "Шматков", "89552332054", "nisare1337@gmail.com"));
        users.add(new User(3, "Максим", "Алексеевич", "Сидоров", "89049193138", "hyperjelle@gmail.com"));
        Mockito.when(mock.findAll()).thenReturn(users);

        Assertions.assertArrayEquals(users.toArray(), mock.findAll().toArray());
    }

    @Test
    void testFindUserById() {
        User userMock = new User(2, "Артем", "Васильевич", "Шматков", "89552332054", "nisare1337@gmail.com");

        Mockito.when(mock.findEntityById(1)).thenReturn(userMock);
        User user = mock.findEntityById(1);
        Assertions.assertEquals(userMock, user);
    }

    @Test
    void testJoin() {
        List<String> list = List.of("First Name: Иван. Last Name: Минеев. Item: G Pro Wireless");
        Mockito.when(mock.innerJoinWithOrders()).thenReturn(list);
        Assertions.assertEquals(list, mock.innerJoinWithOrders());
    }

    @Test
    void testDeleteUser() {
        Mockito.when(mock.delete(2)).thenReturn(true);
        Assertions.assertTrue(mock.delete(2));
    }

    @Test
    void testCreateUser() {
        User user = new User();
        user.setFirstName("Александр");
        user.setSecondName("Александрович");
        user.setLastName("Семенов");
        user.setPhone("89452457654");
        user.setEmail("lavehafeed@gmail.com");

        Mockito.when(mock.create(user)).thenReturn(true);

        Assertions.assertTrue(mock.create(user));
    }

    @Test
    void testCreateUserIsAlreadyExist() {
        Mockito.when(mock.create(new User(4, "Александр", "Александрович", "Семенов", "89552332054", "lavehafeed@gmail.com"))).thenThrow(ConstraintViolationException.class);
        Assertions.assertThrows(ConstraintViolationException.class, () -> mock.create(new User(4, "Александр", "Александрович", "Семенов", "89552332054", "lavehafeed@gmail.com")));
    }

    @Test
    void testUpdateUser() {
        User userExpected = new User(3, "Александр", "Викторович", "Вергус", "89045135358", "dawpaw@gmail.com");

        Mockito.when(mock.update(userExpected)).thenReturn(userExpected);

        Assertions.assertEquals(userExpected, mock.update(userExpected));
    }
}
