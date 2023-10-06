package spring;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.aston.mineev_ia.task10.spring.beans.PrototypeBean;
import ru.aston.mineev_ia.task10.spring.config.Config;

public class AnnotationConfigTest {

    @Test
    void annotationContestTest() {
        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);

        System.out.println(applicationContext.isSingleton("singletonBean"));
        PrototypeBean prototypeBean = (PrototypeBean) applicationContext.getBean("prototypeBean");
        System.out.println(applicationContext.isPrototype("prototypeBean"));
        //prototypeBean.destroyMethod();
        applicationContext.close();
    }

}
