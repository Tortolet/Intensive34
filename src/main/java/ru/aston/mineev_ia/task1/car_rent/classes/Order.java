package ru.aston.mineev_ia.task1.car_rent.classes;

import ru.aston.mineev_ia.task1.car_rent.interfaces.Discount;

import java.math.BigDecimal;
import java.util.Comparator;

public abstract class Order implements Discount {

    private int id;
    private BigDecimal coefficient;
    private BigDecimal costPerMinute;
    private User user;
    private Car car;

    public Order(int id, BigDecimal coefficient, BigDecimal costPerMinute, User user, Car car) {
        this.id = id;
        this.coefficient = coefficient;
        this.costPerMinute = costPerMinute;
        this.user = user;
        this.car = car;
    }

    public abstract BigDecimal getResultAmount(int minutes);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }

    public BigDecimal getCostPerMinute() {
        return costPerMinute;
    }

    public void setCostPerMinute(BigDecimal costPerMinute) {
        this.costPerMinute = costPerMinute;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public static Comparator<Order> surNameComparator = new Comparator<Order>() {
        @Override
        public int compare(Order o1, Order o2) {
            return o1.getUser().getSurName().compareTo(o2.getUser().getSurName());
        }
    };

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", coefficient=" + coefficient +
                ", costPerMinute=" + costPerMinute +
                ", user=" + user +
                ", car=" + car +
                '}';
    }
}
