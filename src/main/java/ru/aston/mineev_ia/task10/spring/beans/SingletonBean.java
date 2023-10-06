package ru.aston.mineev_ia.task10.spring.beans;

public class SingletonBean {

    public void initMethod() {
        System.out.println("SingletonBean is initialized.");
    }

    public void destroyMethod() {
        System.out.println("SingletonBean is destroyed.");
    }
}
