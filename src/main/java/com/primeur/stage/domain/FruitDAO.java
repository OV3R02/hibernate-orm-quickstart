package com.primeur.stage.domain;

import com.primeur.stage.domain.dto.Fruit;
import com.primeur.stage.port.FruitStorageService;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

public class FruitDAO implements FruitStorageService {

	@Inject
    EntityManager entityManager;

	@Override
	public Fruit create(Fruit fruit) {
		entityManager.persist(fruit);
		return fruit;
	}

}
