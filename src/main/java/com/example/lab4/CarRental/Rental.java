package com.example.lab4.CarRental;

import java.time.LocalDate;

public class Rental {

    private final LocalDate from;
    private final LocalDate to;
    private final int clientId;
    private final String vin;

    public  Rental(LocalDate from, LocalDate to, int clientId, String vin) {
            this.from = from;
            this.to = to;
            this.clientId = clientId;
            this.vin = vin;
    }

    public LocalDate getFrom() {return from;}
    public LocalDate getTo() {return to;}
    public int getClientId() {return clientId;}
    public String getVin() {return vin;}

}
