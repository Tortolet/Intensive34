package ru.aston.mineev_ia.task1.car_rent.models;

import ru.aston.mineev_ia.task1.car_rent.interfaces.Rent;
import ru.aston.mineev_ia.task1.car_rent.types.CarType;

public class Car implements Rent {

    private String carName;
    private String model;
    private int horsePower;
    private boolean isRented;
    private double distance;
    private double weight;
    private final CarType carType;

    public Car(String carName, String model, int horsePower, double distance, double weight, CarType carType) {
        this.carName = carName;
        this.model = model;
        this.horsePower = horsePower;
        this.distance = distance;
        this.weight = weight;
        this.carType = carType;
        this.isRented = false;

        boolean notPassCar = weight > 2500 && carType.equals(CarType.PASSENGER_CAR);
        boolean isInvalidValues = horsePower <= 0 || distance < 0 || weight <= 0;

        if (notPassCar) {
            throw new RuntimeException("Легковой автомобиль не подходит по габаритам.");
        }

        if (isInvalidValues){
            throw new RuntimeException("Неверные данные");
        }
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public CarType getCarType() {
        return carType;
    }

    @Override
    public void rent() {
        this.setRented(true);
    }

    @Override
    public void stopRent() {
        this.setRented(false);
    }

    @Override
    public String toString() {
        return "Car{" +
                "carName='" + carName + '\'' +
                ", model='" + model + '\'' +
                ", horsePower=" + horsePower +
                ", isRented=" + isRented +
                ", distance=" + distance +
                ", weight=" + weight +
                ", carType=" + carType +
                '}';
    }
}
