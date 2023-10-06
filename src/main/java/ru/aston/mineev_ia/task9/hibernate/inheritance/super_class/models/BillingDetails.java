package ru.aston.mineev_ia.task9.hibernate.inheritance.super_class.models;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BillingDetails {

    private String owner;

    public BillingDetails() {
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "BillingDetails{" +
                "owner='" + owner + '\'' +
                '}';
    }
}
