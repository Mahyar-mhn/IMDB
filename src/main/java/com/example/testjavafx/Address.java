package com.example.testjavafx;

public class Address {
    private String provinceName;
    private String townName;
    private String streetName;
    private int plaque;

    public Address(String provinceName, String townName, String streetName, int plaque) {
        this.provinceName = provinceName;
        this.townName = townName;
        this.streetName = streetName;
        this.plaque = plaque;
    }
}

