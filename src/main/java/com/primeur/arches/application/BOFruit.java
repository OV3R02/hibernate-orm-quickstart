package com.primeur.arches.application;

import com.primeur.arches.application.mapper.EntFruitMapper;
import com.primeur.arches.application.mapper.VOFruitMapper;
import com.primeur.arches.application.vo.VOFruit;
import com.primeur.arches.domain.dto.EntFruit;
import com.primeur.arches.ports.FruitService;
import com.primeur.arches.ports.FruitStorageService;
import jakarta.inject.Inject;

public class BOFruit implements FruitService {

    @Inject
    FruitStorageService fruitStorageService;

    @Override
    public VOFruit create(VOFruit voFruit) {

        EntFruitMapper entMapper = new EntFruitMapper(voFruit);
        EntFruit entFruit = fruitStorageService.create(entMapper.getEntity());
        VOFruitMapper voMapper = new VOFruitMapper(entFruit);

        return voMapper.getEntity();
    }
}
