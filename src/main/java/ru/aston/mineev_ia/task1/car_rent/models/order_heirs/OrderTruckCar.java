package ru.aston.mineev_ia.task1.car_rent.models.order_heirs;

import ru.aston.mineev_ia.task1.car_rent.models.Car;
import ru.aston.mineev_ia.task1.car_rent.models.Order;
import ru.aston.mineev_ia.task1.car_rent.models.User;
import ru.aston.mineev_ia.task1.car_rent.types.CarType;
import ru.aston.mineev_ia.task2.exceptions.CarAlreadyRented;
import ru.aston.mineev_ia.task2.exceptions.InvalidCarType;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderTruckCar extends Order {
    public OrderTruckCar(int id, BigDecimal coefficient, BigDecimal costPerMinute, User user, Car car) {
        super(id, coefficient, costPerMinute, user, car);

        if (car.getCarType().equals(CarType.PASSENGER_CAR)) {
            throw new InvalidCarType("Машина не является грузовой");
        }

        if (car.isRented()) {
            throw new CarAlreadyRented("Машина уже арендована");
        }

        car.rent();
    }

    @Override
    public BigDecimal getDiscount() {
        boolean isDiscountPresent = getCoefficient().compareTo(new BigDecimal(0)) != 0;

        if (isDiscountPresent) {
            BigDecimal finalPercent = BigDecimal.valueOf(100).subtract(getCoefficient());
            BigDecimal res = getCostPerMinute().multiply(finalPercent);
            return res.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        }

        return getCostPerMinute();
    }

    @Override
    public BigDecimal getResultAmount(int min) {
        boolean discountNotPresent = getCoefficient().compareTo(new BigDecimal(0)) == 0;
        getCar().stopRent();

        if (discountNotPresent) {
            return getCostPerMinute().multiply(BigDecimal.valueOf(min)).setScale(2, RoundingMode.HALF_UP);
        } else {
            return getDiscount().multiply(BigDecimal.valueOf(min));
        }
    }
}
