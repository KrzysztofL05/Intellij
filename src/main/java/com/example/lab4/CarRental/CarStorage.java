package com.example.lab4.CarRental;

import org.springframework.stereotype.Component;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CarStorage {
    private final ArrayList<Car> cars = new ArrayList<>();

    public CarStorage() {
        cars.add( new Car("bmw", "e36", "1FAFP49Y04F160099", "D"));
        cars.add( new Car("bmw", "e36", "1FBDP49Y04F160789", "D"));
        cars.add( new Car("mercedes", "s class", "1J4FY29S2RP486375", "E"));
        cars.add( new Car("opel", "vectra", "1G2ZG528354182137", "C"));
        cars.add( new Car("ford", "mondeo", "SALVP1BG7FH917487", "C"));
        cars.add( new Car("porsche", "911", "WDDNG8GBXAA352284", "S"));
        cars.add( new Car("bugatti", "chiron", "1C4RJEBG4CC239503", "S"));
        cars.add( new Car("lotus", "esprit", "1FDXK84A6KVA81064", "E"));
        cars.add( new Car("toyota", "supra", "1HSRDGMR4PH568087", "S"));
        cars.add( new Car("lancia", "delta", "1C4NJDBB7ED565290", "E"));
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public List<Map.Entry<String, String>> getCarModelAndVinsByModel(String model) {
        return this.cars.stream()
                .filter(car -> car.getModel().equalsIgnoreCase(model))
                .map(car -> new AbstractMap.SimpleEntry<>(
                        car.getModel(),
                        car.getVin()
                ))
                .collect(Collectors.toList());
    }

}
