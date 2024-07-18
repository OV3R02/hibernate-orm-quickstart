package com.primeur.arches.domain.dto;

import com.primeur.arches.ports.FruitStorageService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class FruitDAO implements FruitStorageService {

    @Inject
    EntityManager entityManager;

    @Override
    public EntFruit create(EntFruit fruit) {
        entityManager.persist(fruit);
        return fruit;
    }

    @Override
    public List<EntFruit> get() {
        List<EntFruit> fruitsList = entityManager.createNamedQuery("Fruits.findAll", EntFruit.class)
                .getResultList();
        return fruitsList;
    }


}
