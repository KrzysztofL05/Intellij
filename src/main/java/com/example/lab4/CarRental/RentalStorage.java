package com.example.lab4.CarRental;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RentalStorage {

    private final ArrayList<Rental> rentals = new ArrayList<>();

    public RentalStorage() {
        rentals.add( new Rental(LocalDate.of(2025,12,1), LocalDate.of(2025,12,7), 1, "1FAFP49Y04F160099"));
        rentals.add( new Rental(LocalDate.of(2025,12,4), LocalDate.of(2025, 12, 6), 2, "1FBDP49Y04F160789"));
        rentals.add( new Rental(LocalDate.of(2025,11,1), LocalDate.of(2025, 11, 30), 3, "1J4FY29S2RP486375"));
    }

    public ArrayList<Rental> getRentals() {
        return rentals;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public List<RentalEntry> getRentalByDate(String vin) {
        return this.rentals.stream()
                .filter(rental -> rental.getVin().equalsIgnoreCase(vin))
                .map(rental -> new RentalEntry(rental.getFrom(), rental.getTo()))
                .collect(Collectors.toList());
    }

    public record RentalEntry(LocalDate from, LocalDate to) {}

}
