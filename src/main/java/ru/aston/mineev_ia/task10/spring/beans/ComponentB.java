package ru.aston.mineev_ia.task10.spring.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class ComponentB {

    private final ComponentA componentA;

    @Autowired
    @Lazy
    public ComponentB(ComponentA componentA) {
        this.componentA = componentA;
    }

    public void hello() {
        System.out.println("Hello from B");
    }

    public void helloComponent() {
        componentA.hello();
    }
}
