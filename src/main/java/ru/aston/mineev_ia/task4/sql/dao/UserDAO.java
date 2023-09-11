package ru.aston.mineev_ia.task4.sql.dao;

import java.util.List;

public interface UserDAO<User> {
    List<User> findAll();
    User findEntityById(int id);
    boolean delete(int id);
    boolean create(User t);
    User update(User t);
}
