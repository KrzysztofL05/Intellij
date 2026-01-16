package com.example.lab4.CarRental;

import com.example.lab4.CarRental.CarStorage;
import com.example.lab4.CarRental.CarStorage;
import com.example.lab4.CarRental.Rental;
import com.example.lab4.CarRental.RentalService;
import com.example.lab4.CarRental.RentalStorage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RentalServiceTest {

    @Mock
    private CarStorage carStorage;

    @Mock
    private RentalStorage rentalStorage;

    @InjectMocks
    private RentalService rentalService;

    private final LocalDate from = LocalDate.now().plusDays(1);
    private final LocalDate to = LocalDate.now().plusDays(5);

    @Test
    void shouldRentCarWhenAvailable() {
        // given
        when(carStorage.getCarModelAndVinsByModel("e36"))
                .thenReturn(List.of(
                        Map.entry("e36", "VIN123")
                ));

        when(rentalStorage.getRentalByDate("VIN123"))
                .thenReturn(List.of());

        // when
        boolean result = rentalService.rent("e36", 1, from, to);

        // then
        assertTrue(result);
        verify(rentalStorage).addRental(any(Rental.class));
    }

    @Test
    void shouldNotRentWhenDatesAreInvalid() {
        // when
        boolean result = rentalService.rent(
                "e36",
                1,
                LocalDate.now().minusDays(1),
                to
        );

        // then
        assertFalse(result);
        verifyNoInteractions(carStorage, rentalStorage);
    }

    @Test
    void shouldReturnFalseWhenCarIsNotAvailable() {
        // given
        when(carStorage.getCarModelAndVinsByModel("e36"))
                .thenReturn(List.of(
                        Map.entry("e36", "VIN123")
                ));

        when(rentalStorage.getRentalByDate("VIN123"))
                .thenReturn(List.of(
                        new RentalStorage.RentalEntry(
                                from.minusDays(1),
                                to.plusDays(1)
                        )
                ));

        // when
        boolean result = rentalService.rent("e36", 1, from, to);

        // then
        assertFalse(result);
        verify(rentalStorage, never()).addRental(any());
    }

    @Test
    void isAvailableShouldReturnTrueWhenAtLeastOneCarIsFree() {
        // given
        when(carStorage.getCarModelAndVinsByModel("e36"))
                .thenReturn(List.of(
                        Map.entry("e36", "VIN1"),
                        Map.entry("e36", "VIN2")
                ));

        when(rentalStorage.getRentalByDate("VIN1"))
                .thenReturn(List.of(
                        new RentalStorage.RentalEntry(from, to)
                ));

        when(rentalStorage.getRentalByDate("VIN2"))
                .thenReturn(List.of());

        // when
        boolean result = rentalService.isAvailable("e36", from, to);

        // then
        assertTrue(result);
    }
}
