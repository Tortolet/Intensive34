package sql.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import resolvers.UserServiceParameterResolver;
import ru.aston.mineev_ia.task2.exceptions.ConstraintViolationException;
import ru.aston.mineev_ia.task4.sql.models.User;
import ru.aston.mineev_ia.task4.sql.services.ExecuteSqlService;
import ru.aston.mineev_ia.task4.sql.services.H2DatabaseService;
import ru.aston.mineev_ia.task4.sql.services.UserService;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@ExtendWith(UserServiceParameterResolver.class)
public class UserConflictTests {

    private final UserService userService;

    public UserConflictTests(UserService userService) {
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
