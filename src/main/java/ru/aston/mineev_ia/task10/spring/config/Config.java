package ru.aston.mineev_ia.task10.spring.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.aston.mineev_ia.task10.spring.beans.PrototypeBean;
import ru.aston.mineev_ia.task10.spring.beans.SingletonBean;
import ru.aston.mineev_ia.task10.spring.imp.MyImplementation1;
import ru.aston.mineev_ia.task10.spring.imp.MyImplementation2;
import ru.aston.mineev_ia.task10.spring.interfaces.MyInterface;

@Configuration
@ComponentScan(basePackages = "ru.aston.mineev_ia.task10.spring")
public class Config {
    @Bean(name = "singletonBean", initMethod = "initMethod", destroyMethod = "destroyMethod")
    @Scope("singleton")
    public SingletonBean singletonBean() {
        return new SingletonBean();
    }

    @Bean(name = "prototypeBean", initMethod = "initMethod", destroyMethod = "destroyMethod")
    @Scope("prototype")
    public PrototypeBean prototypeBean() {
        return new PrototypeBean();
    }

    @Bean
    @Qualifier("implementation1")
    public MyInterface implementation1() {
        return new MyImplementation1();
    }

    @Bean
    @Qualifier("implementation2")
    public MyInterface implementation2() {
        return new MyImplementation2();
    }
}
