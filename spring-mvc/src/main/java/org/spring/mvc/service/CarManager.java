package org.spring.mvc.service;

import org.spring.mvc.model.Brand;
import org.spring.mvc.model.Car;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by SDS on 08.04.2016.
 */
@Component
public class CarManager {
    private static List<Car> carList;

    static {
        Brand brand = new Brand();
        brand.setId((long) 1);
        brand.setName("Mercedes");
        brand.setCountry("Germany");
        Brand brand2 = new Brand();

        brand2.setId((long) 2);

        brand2.setName("Peugeot");

        brand2.setCountry("France");

        Car car1 = new Car();

        car1.setId((long) 1);

        car1.setBrand(brand);

        car1.setModel("SL 500");

        car1.setPrice(new BigDecimal(40000));

        Car car2 = new Car();

        car2.setId((long) 2);

        car2.setBrand(brand2);

        car2.setModel("607");

        car2.setPrice(new BigDecimal(35000));

        carList = new LinkedList<Car>();

        carList.add(car1);

        carList.add(car2);

    }

    public List<Car> getCarList() {

        return carList;

    }
    public Car createCar(Car c) {

        Car car = new Car();

        car.setId((long)carList.size() + 1);

        car.setBrand(c.getBrand());

        car.setModel(c.getModel());

        car.setPrice(c.getPrice());

        carList.add(car);

        return car;

    }
}
