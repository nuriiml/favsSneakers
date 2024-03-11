package com.utad.sneakers.service;

import com.utad.sneakers.dto.SneakerDTO;
import com.utad.sneakers.model.Sneaker;
import com.utad.sneakers.model.Stores;
import com.utad.sneakers.repository.SneakerRepository;
import com.utad.sneakers.repository.StoresRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.service.annotation.DeleteExchange;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SneakerService {

    private final SneakerRepository sneakerRepository;
    private final StoresRepository storesRepository;

    @Autowired
    public SneakerService(SneakerRepository sneakerRepository, StoresRepository storesRepository) {
        this.sneakerRepository = sneakerRepository;
        this.storesRepository = storesRepository;
    }

    public List<Sneaker> getSneakers() {
        return sneakerRepository.findAll();
    }

    public List<Stores> getStores () {return storesRepository.findAll(); }

    public Sneaker save(Sneaker sneaker){
        return sneakerRepository.save(sneaker);
    }

    @Transactional
    public Sneaker createSneakerAndStore(SneakerDTO sneakerDTO) {
        // Crear la nueva zapatilla
        Sneaker sneaker = new Sneaker();
        sneaker.setBrand(sneakerDTO.getBrand());
        sneaker.setNumber(sneakerDTO.getNumber());

        // buscarlas por ID
        List<Stores> stores = storesRepository.findAllById(sneakerDTO.getStoreIds());

        // Configurar las tiendas en las que se encuentra la zapatilla
        sneaker.setStores(new HashSet<>(stores));

        // Guardamos la zapatilla con el id de la tienda en la bbdd
        return sneakerRepository.save(sneaker);
    }

    public void deleteSneaker(Long sneakerId) {
        // Obtener la zapatilla a eliminar
        Optional<Sneaker> sneakerOptional = sneakerRepository.findById(sneakerId);

        // Verificar si la zapatilla existe
        if (sneakerOptional.isPresent()) {
            Sneaker sneakerToDelete = sneakerOptional.get();

            // Eliminar la asociación entre la zapatilla y las tiendas
            for (Stores store : sneakerToDelete.getStores()) {
                store.getSneakers().remove(sneakerToDelete);
            }

            // Eliminar la zapatilla de la base de datos
            sneakerRepository.deleteById(sneakerId);
        } else {
            throw new NoSuchElementException("Sneaker not found with ID: " + sneakerId);
        }
    }
}
