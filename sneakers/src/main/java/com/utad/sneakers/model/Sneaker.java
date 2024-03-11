package com.utad.sneakers.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name ="fav_sneakers")

public class Sneaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String brand;
    private double number;

    @ManyToMany
    @JoinTable(name = "sneaker_store",
            joinColumns = @JoinColumn(name = "sneaker_id"),
            inverseJoinColumns = @JoinColumn(name = "store_id"))
    private Set<Stores> stores = new HashSet<>();
}
