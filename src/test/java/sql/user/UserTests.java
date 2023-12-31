package sql.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.aston.mineev_ia.task4.sql.models.User;
import ru.aston.mineev_ia.task4.sql.services.H2DatabaseService;
import ru.aston.mineev_ia.task4.sql.services.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserTests {

    private static final H2DatabaseService H2_DB_SERVICE = new H2DatabaseService();
    private final UserService userService = new UserService(H2_DB_SERVICE);

    @BeforeAll
    static void init() {
        H2_DB_SERVICE.createTables();
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
