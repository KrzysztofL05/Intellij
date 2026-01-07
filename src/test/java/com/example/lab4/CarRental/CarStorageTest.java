package com.example.lab4.CarRental;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class CarStorageTest {
    private CarStorage carStorage =  new CarStorage();

    @Test
    void shouldGetCars() {
        //Given

        //When
        ArrayList<Car> cars = carStorage.getCars();

        //Then
        assertThat(cars).isNotEmpty();
    }

    @Test
    void shouldBeNull() {
        //Given

        //When
        ArrayList<Car> cars = carStorage.getCars();

        //Then
        assertThat(cars).isNotNull();
    }

}