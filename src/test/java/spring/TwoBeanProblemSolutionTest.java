package spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.aston.mineev_ia.task10.spring.config.Config;
import ru.aston.mineev_ia.task10.spring.interfaces.MyInterface;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Config.class)
public class TwoBeanProblemSolutionTest {
    @Autowired
    @Qualifier("implementation2")
    private MyInterface myInterface;

    @Test
    void name() {
        myInterface.doSomething();
    }
}
