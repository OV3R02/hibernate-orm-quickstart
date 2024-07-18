package com.primeur.arches.domain.dto;

import com.primeur.arches.ports.FruitStorageService;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

public class FruitDAO implements FruitStorageService {

    @Inject
    EntityManager entityManager;

    @Override
    public EntFruit create(EntFruit fruit) {
        entityManager.persist(fruit);
        return fruit;
    }
}
