package com.example.lab4.CarRental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class CarRentalApplication {

    public CarRentalApplication(RentalService rentalService) {

        boolean passat = rentalService.isAvailable("passat", LocalDate.of(2025, 12, 13), LocalDate.of(2025, 12, 25));
        boolean vectra = rentalService.isAvailable("vectra", LocalDate.of(2025, 12, 16), LocalDate.of(2025, 12, 19));
        boolean vectra1 = rentalService.rent("vectra", 4, LocalDate.of(2025, 12, 16), LocalDate.of(2025, 12, 19));
        boolean mondeo = rentalService.rent("mondeo", 5, LocalDate.of(2025, 12, 1), LocalDate.of(2025, 12, 4));
        boolean mondeo1 = rentalService.isAvailable("mondeo", LocalDate.of(2025, 12, 24), LocalDate.of(2025, 12, 26));
        boolean sclass = rentalService.isAvailable("s class", LocalDate.of(2025, 12, 2), LocalDate.of(2025, 12, 9));
        boolean sclass1 = rentalService.rent("s class", 4, LocalDate.of(2025, 12, 14), LocalDate.of(2025, 12, 9));
        boolean sclass2 = rentalService.rent("s class", 5, LocalDate.of(2025, 12, 13), LocalDate.of(2025, 12, 18));
        //poprawic kolejnosc ifow najpierw model potem data
    }

	public static void main(String[] args) {
		SpringApplication.run(CarRentalApplication.class, args);
	}

}
