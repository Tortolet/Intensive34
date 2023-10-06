package ru.aston.mineev_ia.task10.spring.beans;

public class PrototypeBean {

    public void initMethod() {
        System.out.println("PrototypeBean is initialized.");
    }

    public void destroyMethod() {
        System.out.println("PrototypeBean is destroyed.");
    }
}
