package ru.aston.mineev_ia.task9.hibernate.relations.one_to_many;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import ru.aston.mineev_ia.task9.hibernate.relations.enums.Roles;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Roles roles;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "position_id")
    private Set<Employee> employees = new LinkedHashSet<>();

    public Position() {
    }

    public void addEmployeePos(Employee employee){
        this.employees.add(employee);
    }

    public void removeEmployeePos(Employee employee){
        this.employees.remove(employee);
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

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roles=" + roles +
                '}';
    }
}
