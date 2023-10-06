package ru.aston.mineev_ia.task10.spring.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/application.properties")
public class MyComponent {

    @Value("${custom.setting}")
    private String customProperty;

    public void doSomething() {
        System.out.println("Res: " + customProperty);
    }
}
