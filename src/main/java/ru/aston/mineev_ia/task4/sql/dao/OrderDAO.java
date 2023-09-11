package ru.aston.mineev_ia.task4.sql.dao;

import java.util.List;

public interface OrderDAO<Order> {
    List<Order> findAll();
    Order findEntityById(int id);
    boolean delete(int id);
    boolean create(Order t);
    Order update(Order t);
}
