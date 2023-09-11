package sql.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import resolvers.UserServiceParameterResolver;
import ru.aston.mineev_ia.task4.sql.models.User;
import ru.aston.mineev_ia.task4.sql.services.ExecuteSqlService;
import ru.aston.mineev_ia.task4.sql.services.H2DatabaseService;
import ru.aston.mineev_ia.task4.sql.services.UserService;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(UserServiceParameterResolver.class)
public class UserTests {

    private final UserService userService;

    public UserTests(UserService userService) {
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
    void testAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Иван", "Андреевич", "Минеев", "89126144959", "ivanmineev52@gmail.com"));
        users.add(new User(2, "Артем", "Васильевич", "Шматков", "89552332054", "nisare1337@gmail.com"));
        users.add(new User(3, "Александр", "Викторович", "Вергус", "89045135358", "polarxtreame@gmail.com"));

        Assertions.assertArrayEquals(users.toArray(), userService.findAll().toArray());

    }

    @Test
    void testFindUserById() {
        User user = userService.findEntityById(1);
        User userExpected = new User(1, "Иван", "Андреевич", "Минеев", "89126144959", "ivanmineev52@gmail.com");
        Assertions.assertEquals(userExpected, user);
    }




}
