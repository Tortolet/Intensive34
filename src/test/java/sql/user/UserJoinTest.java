package sql.user;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.aston.mineev_ia.task4.sql.services.H2DatabaseService;
import ru.aston.mineev_ia.task4.sql.services.UserService;

public class UserJoinTest {

    private static final H2DatabaseService H2_DB_SERVICE = new H2DatabaseService();
    private final UserService userService = new UserService(H2_DB_SERVICE);

    @BeforeAll
    static void init() {
        H2_DB_SERVICE.createTables();
    }

    @Test
    void testJoin() {
        System.out.println(userService.innerJoinWithOrders());
    }
}
