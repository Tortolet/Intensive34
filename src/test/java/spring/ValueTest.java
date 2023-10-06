package spring;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.aston.mineev_ia.task10.spring.beans.MyComponent;

public class ValueTest {

    @Test
    void testValue() {
        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyComponent.class);

        MyComponent myComponent = (MyComponent) applicationContext.getBean("myComponent");
        myComponent.doSomething();
    }
}
