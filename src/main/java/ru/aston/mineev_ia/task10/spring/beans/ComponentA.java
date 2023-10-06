package ru.aston.mineev_ia.task10.spring.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class ComponentA {

    private final ComponentB componentB;

    @Autowired
    @Lazy
    public ComponentA(ComponentB componentB) {
        this.componentB = componentB;
    }

    public void hello() {
        System.out.println("Hello from A");
    }

    public void helloComponent() {
        componentB.hello();
    }
}
