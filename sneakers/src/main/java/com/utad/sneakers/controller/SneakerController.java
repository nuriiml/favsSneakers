package com.utad.sneakers.controller;

import com.utad.sneakers.dto.SneakerDTO;
import com.utad.sneakers.model.Sneaker;
import com.utad.sneakers.model.Stores;
import com.utad.sneakers.service.SneakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/sneakers")
public class SneakerController {

    private final SneakerService sneakerService;

    @Autowired
    public SneakerController(SneakerService sneakerService) {
        this.sneakerService = sneakerService;
    }

    @GetMapping
    public ResponseEntity<List<Sneaker>> getAll(){
        List<Sneaker> list = sneakerService.getSneakers();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<Sneaker> save(@RequestBody Sneaker plato) {
        Sneaker newSneaker = sneakerService.save(plato);
        return ResponseEntity.ok(newSneaker);
    }

    @GetMapping("/boolean")
    public ResponseEntity<Boolean> allSneakers() {
        return ResponseEntity.ok(true);
    }

    @GetMapping("/allStores")
    public ResponseEntity<List<Stores>> getAllStores(){
        List<Stores> list = sneakerService.getStores();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/final")
    public ResponseEntity<Sneaker> addSneakerAndStore(@RequestBody SneakerDTO req) {
        Sneaker sneakerCreated = sneakerService.createSneakerAndStore(req);
        return ResponseEntity.ok(sneakerCreated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSneaker(@PathVariable Long id) {
        sneakerService.deleteSneaker(id);
        return ResponseEntity.noContent().build();
    }
}
