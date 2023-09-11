package sql.user;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import resolvers.UserServiceParameterResolver;
import ru.aston.mineev_ia.task4.sql.services.ExecuteSqlService;
import ru.aston.mineev_ia.task4.sql.services.H2DatabaseService;
import ru.aston.mineev_ia.task4.sql.services.UserService;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@ExtendWith(UserServiceParameterResolver.class)
public class UserJoinTest {

    private final UserService userService;

    public UserJoinTest(UserService userService) {
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
    void testJoin() {
        System.out.println(userService.innerJoinWithOrders());
    }
}
