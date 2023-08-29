package ru.aston.mineev_ia.task1.car_rent.classes;

import java.util.Objects;

public class User {

    private int age;
    private String surName;
    private String name;

    public User(int age, String surName, String name) {
        this.age = age;
        this.surName = surName;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && Objects.equals(surName, user.surName) && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, surName, name);
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", surName='" + surName + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
