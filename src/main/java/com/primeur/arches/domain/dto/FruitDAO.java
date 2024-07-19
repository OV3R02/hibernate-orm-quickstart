package com.primeur.arches.domain.dto;

import com.primeur.arches.application.vo.VOFruit;
import com.primeur.arches.ports.FruitStorageService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.WebApplicationException;

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
        return entityManager.createNamedQuery("Fruits.findAll", EntFruit.class)
                .getResultList();
    }

    @Override
    public EntFruit getSingle(Integer id) {
        return entityManager.find(EntFruit.class, id);
    }

    @Override
    public EntFruit delete(Integer id) {
        EntFruit entity = entityManager.getReference(EntFruit.class, id);
        entityManager.remove(entity);
        return entity;
    }

    @Override
    public EntFruit update(EntFruit entFruit, Integer id) {
        EntFruit newEntfruit = entityManager.find(EntFruit.class, id);
        newEntfruit.setName(entFruit.getName());
        return newEntfruit;
    }


}
