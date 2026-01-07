package com.example.lab4.CarRental;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class RentalService {

    private final CarStorage carStorage;
    private final RentalStorage rentalStorage;

    public RentalService(CarStorage carStorage, RentalStorage rentalStorage) {
        this.carStorage = carStorage;
        this.rentalStorage = rentalStorage;
    }

    public boolean rent(String model, int clientId, LocalDate from, LocalDate to) {

        if (!dateValidation(from, to)) {
            System.out.println("Niepoprawny zakres dat.");
            return false;
        }

        if (!isAvailable(model, from, to)) {
            System.out.println("Brak dostępnych aut modelu: " + model);
            return false;
        }

        var cars = carStorage.getCarModelAndVinsByModel(model);

        for (var entry : cars) {
            String vin = entry.getValue();

            var rentals = rentalStorage.getRentalByDate(vin);

            boolean available = rentals.stream().noneMatch(r -> {
                LocalDate rentedFrom = r.from();
                LocalDate rentedTo = r.to();
                return !(to.isBefore(rentedFrom) || from.isAfter(rentedTo));
            });

            if (available) {
                Rental newRental = new Rental(from, to, clientId, vin);
                rentalStorage.addRental(newRental);

                System.out.println("Zarezerwowano auto " + model
                        + " VIN: " + vin
                        + " dla klienta ID: " + clientId
                        + " W terminie od " + from
                        + " do " + to);
                return true;
            }
        }
        return false;
    }

    public boolean isAvailable(String model, LocalDate from, LocalDate to) {


        if (!dateValidation(from, to)) {
            System.out.println("Niepoprawny zakres dat.");
            return false;
        }

        var cars = carStorage.getCarModelAndVinsByModel(model);

        if (cars.isEmpty()) {
            System.out.println("Brak takiego modelu.");
            return false;
        }

        for (var entry : cars) {
            String vin = entry.getValue();

            var rentals = rentalStorage.getRentalByDate(vin);

            boolean available = true;

            for (var rentalDates : rentals) {
                LocalDate rentedFrom = rentalDates.from();
                LocalDate rentedTo = rentalDates.to();

                boolean overlaps = !(to.isBefore(rentedFrom) || from.isAfter(rentedTo));

                if (overlaps) {
                    available = false;
                    break;
                }
            }

            if (available) {
                System.out.println("Auto dostępne.");
                return true;
            }
        }

        return false;
    }


    private boolean dateValidation(LocalDate from, LocalDate to) {

        if (from == null || to == null) {
            return false;
        }

        if (from.isAfter(to)) {
            return false;
        }

        if (from.isBefore(LocalDate.now())) {
            return false;
        }

        return true;
    }

}
