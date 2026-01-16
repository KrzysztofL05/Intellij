package com.example.lab4.CarRental;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarRentalIntegrationTest {

    @Autowired
    private RentalService rentalService;

    @Autowired
    private CarStorage carStorage;

    @Autowired
    private RentalStorage rentalStorage;

    @Test
    void integrationTestRentAndAvailability() {
        // Sprawdź, że samochody istnieją w storage
        assertFalse(carStorage.getCars().isEmpty(), "CarStorage powinien zawierać samochody");

        // Sprawdź dostępność samochodu przed wypożyczeniem
        boolean bmwAvailable = rentalService.isAvailable(
                "e36",
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(5)
        );
        assertTrue(bmwAvailable, "BMW e36 powinno być dostępne");

        // Wypożycz samochód e36
        boolean rentedBmw1 = rentalService.rent(
                "e36",
                1,
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(5)
        );
        assertTrue(rentedBmw1, "Pierwsze wypożyczenie BMW e36 powinno się powieść");

        // Wypożycz ponownie e36
        boolean rentedBmw2 = rentalService.rent(
                "e36",
                2,
                LocalDate.now().plusDays(2),
                LocalDate.now().plusDays(4)
        );
        assertTrue(rentedBmw2, "Drugie wypożyczenie BMW e36 powinno się powieść");

        // Próba wypożyczenia e36 gdy wszystkie egzemplarze zajęte
        boolean rentedBmwConflict = rentalService.rent(
                "e36",
                3,
                LocalDate.now().plusDays(2),
                LocalDate.now().plusDays(4)
        );
        assertFalse(rentedBmwConflict, "Konflikt dat powinien zablokować wypożyczenie");

        // Wypożyczenie innego samochodu
        boolean rentedVectra = rentalService.rent(
                "vectra",
                4,
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(3)
        );
        assertTrue(rentedVectra, "Wypożyczenie Opla Vectra powinno się powieść");

        // Sprawdzenie dostępności po wypożyczeniu
        boolean availableVectraLater = rentalService.isAvailable(
                "vectra",
                LocalDate.now().plusDays(4),
                LocalDate.now().plusDays(6)
        );
        assertTrue(availableVectraLater, "Vectra powinna być dostępna w późniejszym terminie");

        // Próba wypożyczenia z błędnymi datami
        boolean rentedInvalidDates = rentalService.rent(
                "mondeo",
                5,
                LocalDate.now().plusDays(5),
                LocalDate.now().plusDays(2)
        );
        assertFalse(rentedInvalidDates, "Niepoprawny zakres dat powinien zablokować wypożyczenie");
    }
}
