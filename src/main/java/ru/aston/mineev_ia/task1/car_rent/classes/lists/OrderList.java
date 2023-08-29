package ru.aston.mineev_ia.task1.car_rent.classes.lists;

import ru.aston.mineev_ia.task1.car_rent.classes.Order;
import ru.aston.mineev_ia.task1.car_rent.interfaces.OrderCalculation;

import java.math.BigDecimal;
import java.util.List;

public class OrderList implements OrderCalculation {

    public static final int INIT_MIN = 60;
    private final List<Order> orderList;

    public OrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public BigDecimal calcAmount() {
        BigDecimal res = new BigDecimal(0);
        for (Order order : orderList) {
            res = res.add(order.getResultAmount(INIT_MIN));
        }

        return res;
    }

    @Override
    public String toString() {
        orderList.sort(Order.surNameComparator);
        return "OrderList{" +
                "orderList=" + orderList +
                '}';
    }
}
