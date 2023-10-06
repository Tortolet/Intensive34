package spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.aston.mineev_ia.task10.spring.beans.ComponentA;
import ru.aston.mineev_ia.task10.spring.beans.ComponentB;
import ru.aston.mineev_ia.task10.spring.config.Config;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Config.class)
public class LazySolutionTest {

    @Autowired
    private ComponentA componentA;

    @Autowired
    private ComponentB componentB;

    @Test
    void test() {
        componentA.helloComponent();
        componentB.helloComponent();
    }
}
