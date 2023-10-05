package ru.aston.mineev_ia.task9.hibernate.relations;

import java.time.LocalDate;

public class Birthday {

    private LocalDate date;

    public Birthday(LocalDate localDate) {
        this.date = localDate;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Birthday{" +
                "date=" + date +
                '}';
    }
}
