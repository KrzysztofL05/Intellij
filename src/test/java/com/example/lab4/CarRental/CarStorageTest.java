package com.example.lab4.CarRental;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CarStorageTest {

    private CarStorage carStorage;

    @BeforeEach
    void setUp() {
        carStorage = new CarStorage();
    }

    @Test
    void shouldReturnAllCars() {
        // when
        List<Car> cars = carStorage.getCars();

        // then
        assertNotNull(cars);
        assertEquals(10, cars.size());
    }

    @Test
    void shouldReturnCarsByModelIgnoringCase() {
        // when
        List<Map.Entry<String, String>> result =
                carStorage.getCarModelAndVinsByModel("E36");

        // then
        assertEquals(2, result.size());

        assertTrue(result.stream()
                .allMatch(entry -> entry.getKey().equalsIgnoreCase("e36")));
    }

    @Test
    void shouldReturnEmptyListWhenModelDoesNotExist() {
        // when
        List<Map.Entry<String, String>> result =
                carStorage.getCarModelAndVinsByModel("unknown");

        // then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnCorrectVinForModel() {
        // when
        List<Map.Entry<String, String>> result =
                carStorage.getCarModelAndVinsByModel("supra");

        // then
        assertEquals(1, result.size());
        assertEquals("supra", result.get(0).getKey());
        assertEquals("1HSRDGMR4PH568087", result.get(0).getValue());
    }

}