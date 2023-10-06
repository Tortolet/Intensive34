package ru.aston.mineev_ia.task9.hibernate.relations.many_to_many;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import ru.aston.mineev_ia.task9.hibernate.relations.Birthday;
import ru.aston.mineev_ia.task9.hibernate.relations.convertors.BirthdayConvertor;

import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(
            name = "employee_positions",
            joinColumns = { @JoinColumn(name = "employee_id") },
            inverseJoinColumns = { @JoinColumn(name = "position_id") }
    )
    private Set<Position> positions = new HashSet<>();

    public Employee() {
    }

    public void addPosition(Position position){
        this.positions.add(position);
    }

    public void removePosition(Position position){
        this.positions.remove(position);
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

    public Set<Position> getPositions() {
        return positions;
    }

    public void setPositions(Set<Position> positions) {
        this.positions = positions;
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
