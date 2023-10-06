package ru.aston.mineev_ia.task10.spring.imp;

import org.springframework.stereotype.Component;
import ru.aston.mineev_ia.task10.spring.interfaces.MyInterface;

@Component("implementation2")
public class MyImplementation2 implements MyInterface {
    @Override
    public void doSomething() {
        System.out.println("MyImplementation2 is doing something.");
    }
}
