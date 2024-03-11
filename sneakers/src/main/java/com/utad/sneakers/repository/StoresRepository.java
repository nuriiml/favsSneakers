package com.utad.sneakers.repository;

import com.utad.sneakers.model.Stores;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoresRepository extends JpaRepository<Stores, Long> {
}
