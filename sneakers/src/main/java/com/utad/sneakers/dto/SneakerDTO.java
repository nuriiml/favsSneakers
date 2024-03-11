package com.utad.sneakers.dto;

import java.util.HashSet;
import java.util.Set;

public class SneakerDTO {
    private String brand;
    private double number;
    private Set<Long> storeIds = new HashSet<>();

    public SneakerDTO(String brand, double number, Set<Long> storeIds) {
        this.brand = brand;
        this.number = number;
        this.storeIds = storeIds;
    }

    // Getters y setters
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public double getNumber() {
        return number;
    }
    public void setNumber(double number) {
        this.number = number;
    }
    public Set<Long> getStoreIds() {
        return storeIds;
    }
    public void setStoreIds(Set<Long> storeIds) {
        this.storeIds = storeIds;
    }
}
