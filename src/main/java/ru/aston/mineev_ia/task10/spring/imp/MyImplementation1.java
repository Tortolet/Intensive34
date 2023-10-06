package ru.aston.mineev_ia.task10.spring.imp;

import org.springframework.stereotype.Component;
import ru.aston.mineev_ia.task10.spring.interfaces.MyInterface;

@Component("implementation1")
public class MyImplementation1 implements MyInterface {
    @Override
    public void doSomething() {
        System.out.println("MyImplementation1 is doing something.");
    }
}
