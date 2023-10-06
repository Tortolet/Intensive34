package spring;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.aston.mineev_ia.task10.spring.beans.PrototypeBean;

public class XmlContextTest {
    @Test
    void xmlContextTest() {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("config.xml");

        System.out.println(applicationContext.isSingleton("singletonBean"));
        PrototypeBean prototypeBean = (PrototypeBean) applicationContext.getBean("prototypeBean");
        System.out.println(applicationContext.isPrototype("prototypeBean"));
        //prototypeBean.destroyMethod();
        applicationContext.close();
    }
}
