package sql.user;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.aston.mineev_ia.task4.sql.services.ExecuteSqlService;
import ru.aston.mineev_ia.task4.sql.services.H2DatabaseService;
import ru.aston.mineev_ia.task4.sql.services.UserService;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UserJoinTest {

    private static final H2DatabaseService H2_DB_SERVICE = new H2DatabaseService();
    private final UserService userService = new UserService(H2_DB_SERVICE);

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
    void testJoin() {
        System.out.println(userService.innerJoinWithOrders());
    }
}
