package sql.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.aston.mineev_ia.task2.exceptions.ConstraintViolationException;
import ru.aston.mineev_ia.task4.sql.models.User;
import ru.aston.mineev_ia.task4.sql.services.H2DatabaseService;
import ru.aston.mineev_ia.task4.sql.services.UserService;

public class UserConflictTests {

    private static final H2DatabaseService H2_DB_SERVICE = new H2DatabaseService();
    private final UserService userService = new UserService(H2_DB_SERVICE);

    @BeforeAll
    static void init() {
        H2_DB_SERVICE.createTables();
    }

    @Test
    void testDeleteUser() {
        Assertions.assertTrue(userService.delete(1));
    }

    @Test
    void testCreateUser() {
        User user = new User();
        user.setFirstName("Александр");
        user.setSecondName("Александрович");
        user.setLastName("Семенов");
        user.setPhone("89452457654");
        user.setEmail("lavehafeed@gmail.com");
        Assertions.assertTrue(userService.create(user));
    }

    @Test
    void testCreateUserIsAlreadyExist() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> userService.create(new User(4, "Александр", "Александрович", "Семенов", "89552332054", "lavehafeed@gmail.com")));
    }

    @Test
    void testUpdateUser() {
        User userExpected = new User(3, "Александр", "Викторович", "Вергус", "89045135358", "dawpaw@gmail.com");
        Assertions.assertEquals(userExpected, userService.update(userExpected));
    }
}
