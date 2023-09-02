import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aston.mineev_ia.task1.car_rent.models.Car;
import ru.aston.mineev_ia.task1.car_rent.models.Order;
import ru.aston.mineev_ia.task1.car_rent.models.User;
import ru.aston.mineev_ia.task1.car_rent.models.lists.OrderList;
import ru.aston.mineev_ia.task1.car_rent.models.order_heirs.OrderPassengerCar;
import ru.aston.mineev_ia.task1.car_rent.models.order_heirs.OrderTruckCar;
import ru.aston.mineev_ia.task1.car_rent.types.CarType;
import ru.aston.mineev_ia.task2.exceptions.CarAlreadyRented;
import ru.aston.mineev_ia.task2.exceptions.InvalidCarType;
import ru.aston.mineev_ia.task2.exceptions.InvalidValues;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MainTests {
    private final List<Order> orderList = new ArrayList<>();

    @BeforeEach
     void start() throws InvalidValues {
        User user1 = new User(20, "Минеев", "Иван");
        User user2 = new User(19, "Шматков", "Артем");
        User user3 = new User(19, "Семенов", "Александр");

        Car car1 = new Car("BMW", "M8 F90", 617, 3.614, 2035, CarType.PASSENGER_CAR);
        Car car2 = new Car("Lexus", "RX", 280, 65.123, 1540, CarType.PASSENGER_CAR);
        Car car3 = new Car("Nissan", "Atleon", 150, 65.123, 1540, CarType.TRUCK_CAR);

        orderList.add(new OrderTruckCar(1, new BigDecimal(5), new BigDecimal("5.00"), user2, car3));
        orderList.add(new OrderPassengerCar(3, new BigDecimal(5), new BigDecimal("5.00"), user1, car1));
        orderList.add(new OrderPassengerCar(2, new BigDecimal(5), new BigDecimal("5.00"), user3, car2));
    }

    @Test
    void testSort() {
        orderList.sort(Order.surNameComparator);
        Assertions.assertEquals("Минеев", orderList.get(0).getUser().getSurName());
        Assertions.assertEquals("Шматков", orderList.get(2).getUser().getSurName());
    }

    @Test
    void testTypeCar() {
        Assertions.assertThrows(InvalidCarType.class, () -> new Car("BMW", "M8 F90", 617, 3.614, 2700, CarType.PASSENGER_CAR));
    }

    @Test
    void testRentCar() {
        Assertions.assertTrue(orderList.get(0).getCar().isRented());
    }

    @Test
    void testRentCarException() {
        Assertions.assertThrows(CarAlreadyRented.class, () -> new OrderTruckCar(4, new BigDecimal(5), new BigDecimal("5.00"), orderList.get(0).getUser(), orderList.get(0).getCar()));
    }

    @Test
    void testUnRentCar() {
        orderList.get(0).getCar().stopRent();
        Assertions.assertFalse(orderList.get(0).getCar().isRented());
    }

    @Test
    void testDiscount() {
        Assertions.assertEquals(new BigDecimal("190.00"), orderList.get(0).getResultAmount(40));
    }

    @Test
    void testWithoutDiscount() throws InvalidValues {
        Car car = new Car("BMW", "M8 F90", 617, 3.614, 1980, CarType.TRUCK_CAR);
        User user = new User(40, "Вергус", "Александр");
        orderList.add(new OrderTruckCar(4, new BigDecimal(0), new BigDecimal("9.00"), user, car));

        Assertions.assertEquals(new BigDecimal("360.00"), orderList.get(3).getResultAmount(40));
        Assertions.assertFalse(orderList.get(3).getCar().isRented());
    }

    @Test
    void testOrderList() {
        OrderList list = new OrderList(orderList);
        Assertions.assertEquals(new BigDecimal("855.00"), list.calcAmount());
    }

    @Test
    void testSortBySurname() {
        OrderList list = new OrderList(orderList);
        String listToString = list.toString();

        Assertions.assertEquals("OrderList{orderList=[Order{id=3, coefficient=5, costPerMinute=5.00, user=User{age=20, surName='Минеев', name='Иван'}, car=Car{carName='BMW', model='M8 F90', horsePower=617, isRented=true, distance=3.614, weight=2035.0, carType=PASSENGER_CAR}}, Order{id=2, coefficient=5, costPerMinute=5.00, user=User{age=19, surName='Семенов', name='Александр'}, car=Car{carName='Lexus', model='RX', horsePower=280, isRented=true, distance=65.123, weight=1540.0, carType=PASSENGER_CAR}}, Order{id=1, coefficient=5, costPerMinute=5.00, user=User{age=19, surName='Шматков', name='Артем'}, car=Car{carName='Nissan', model='Atleon', horsePower=150, isRented=true, distance=65.123, weight=1540.0, carType=TRUCK_CAR}}]}", listToString);
        Assertions.assertEquals("Шматков", orderList.get(2).getUser().getSurName());
    }

    @Test
    void testInvalidCar() {
        Assertions.assertThrows(InvalidValues.class, () -> new Car("BMW", "M8 F90", 7, -3.614, 1980, CarType.TRUCK_CAR));
    }

}
