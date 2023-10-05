package ru.aston.mineev_ia.task9.hibernate.relations.one_to_one;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import ru.aston.mineev_ia.task9.hibernate.relations.Birthday;
import ru.aston.mineev_ia.task9.hibernate.relations.convertors.BirthdayConvertor;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NotNull
    private String name;

    @NotNull
    @Convert(converter = BirthdayConvertor.class)
    private Birthday birthday;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "position_id")
    private Position position;

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Birthday getBirthday() {
        return birthday;
    }

    public void setBirthday(Birthday birthday) {
        this.birthday = birthday;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
