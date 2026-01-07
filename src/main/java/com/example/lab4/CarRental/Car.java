package com.example.lab4.CarRental;

public class Car {

    private String brand;
    private String model;
    private String vin;
    private String standard;

    public Car(String brand, String model, String vin, String standard) {
        this.brand = brand;
        this.model = model;
        this.vin = vin;
        this.standard = standard;
    }
    public String getBrand() {return brand;}
    public String getModel() {return model;}
    public String getVin() {return vin;}
    public String getStandard() {return standard;}

}
