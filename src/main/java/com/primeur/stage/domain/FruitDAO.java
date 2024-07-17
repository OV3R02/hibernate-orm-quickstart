package com.primeur.stage.domain;

import com.primeur.stage.domain.dto.Fruit;
import com.primeur.stage.port.FruitStorageService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class FruitDAO implements FruitStorageService {

	@Inject
    EntityManager entityManager;

	@Override
	public Fruit create(Fruit fruit) {
		entityManager.persist(fruit);
		return fruit;
	}

}
