package com.example.lab4.CarRental;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RentalStorageTest {

    private RentalStorage rentalStorage;

    @BeforeEach
    void setUp() {
        rentalStorage = new RentalStorage();
    }

    @Test
    void shouldReturnAllRentals() {
        // when
        List<Rental> rentals = rentalStorage.getRentals();

        // then
        assertNotNull(rentals);
        assertEquals(3, rentals.size());
    }

    @Test
    void shouldAddNewRental() {
        // given
        Rental rental = new Rental(
                LocalDate.of(2025, 12, 10),
                LocalDate.of(2025, 12, 15),
                4,
                "TESTVIN123"
        );

        // when
        rentalStorage.addRental(rental);

        // then
        assertEquals(4, rentalStorage.getRentals().size());
        assertTrue(rentalStorage.getRentals().contains(rental));
    }

    @Test
    void shouldReturnRentalEntriesByVin() {
        // when
        List<RentalStorage.RentalEntry> result =
                rentalStorage.getRentalByDate("1FAFP49Y04F160099");

        // then
        assertEquals(1, result.size());

        RentalStorage.RentalEntry entry = result.get(0);
        assertEquals(LocalDate.of(2025, 12, 1), entry.from());
        assertEquals(LocalDate.of(2025, 12, 7), entry.to());
    }

    @Test
    void shouldIgnoreCaseWhenFilteringByVin() {
        // when
        List<RentalStorage.RentalEntry> result =
                rentalStorage.getRentalByDate("1fafp49y04f160099");

        // then
        assertEquals(1, result.size());
    }

    @Test
    void shouldReturnEmptyListWhenVinNotFound() {
        // when
        List<RentalStorage.RentalEntry> result =
                rentalStorage.getRentalByDate("UNKNOWNVIN");

        // then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}