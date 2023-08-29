package ru.aston.mineev_ia.task1.car_rent.classes.order_heirs;

import ru.aston.mineev_ia.task1.car_rent.classes.Car;
import ru.aston.mineev_ia.task1.car_rent.classes.Order;
import ru.aston.mineev_ia.task1.car_rent.classes.User;
import ru.aston.mineev_ia.task1.car_rent.types.CarType;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderTruckCar extends Order {
    public OrderTruckCar(int id, BigDecimal coefficient, BigDecimal costPerMinute, User user, Car car) throws Exception {
        super(id, coefficient, costPerMinute, user, car);

        if (car.getCarType().equals(CarType.PASSENGER_CAR)) {
            throw new Exception("Машина не является грузовой");
        }

        if (car.isRented()) {
            throw new Exception("Машина уже арендована");
        }

        car.rent();
    }

    @Override
    public BigDecimal getDiscount() {
        if (getCoefficient().compareTo(new BigDecimal(0)) != 0) {
            BigDecimal finalPercent = BigDecimal.valueOf(100).subtract(getCoefficient());
            BigDecimal res = getCostPerMinute().multiply(finalPercent);
            return res.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        }

        return getCostPerMinute();
    }

    @Override
    public BigDecimal getResultAmount(int min) {
        if (getCoefficient().compareTo(new BigDecimal(0)) == 0) {
            getCar().stopRent();
            return getCostPerMinute().multiply(BigDecimal.valueOf(min)).setScale(2, RoundingMode.HALF_UP);
        } else {
            getCar().stopRent();
            return getDiscount().multiply(BigDecimal.valueOf(min));
        }
    }
}
