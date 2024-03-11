package com.utad.sneakers.repository;

import com.utad.sneakers.model.Sneaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SneakerRepository extends JpaRepository<Sneaker, Long> {
}
