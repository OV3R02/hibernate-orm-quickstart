package com.primeur.arches.domain.dto;

import com.primeur.arches.ports.FruitStorageService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

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
        return entityManager.createNamedQuery("Fruits.findAll", EntFruit.class)
                .getResultList();
    }

    @Override
    public Optional<EntFruit> getSingle(Integer id) {
        return Optional.ofNullable(entityManager.find(EntFruit.class, id));
    }

    @Override
    public EntFruit update(EntFruit entFruit, Integer id) {
        EntFruit newEntfruit = entityManager.find(EntFruit.class, id);
        newEntfruit.setName(entFruit.getName());
        entityManager.persist(newEntfruit);
        return newEntfruit;
    }

    @Override
    public void delete(EntFruit entFruit) {
        entityManager.remove(entFruit);
    }


}
