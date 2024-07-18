package com.primeur.arches.application;

import com.primeur.arches.application.mapper.EntFruitMapper;
import com.primeur.arches.application.mapper.VOFruitMapper;
import com.primeur.arches.application.vo.VOFruit;
import com.primeur.arches.domain.dto.EntFruit;
import com.primeur.arches.ports.FruitService;
import com.primeur.arches.ports.FruitStorageService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
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

    @Override
    public List<VOFruit> get() {
        List<EntFruit> entFruitsList = fruitStorageService.get();
        List<VOFruit> voFruitList = new ArrayList<>();
        for (EntFruit entFruit : entFruitsList) {
            VOFruitMapper voFruitMapper = new VOFruitMapper(entFruit);
            VOFruit voFruit = voFruitMapper.getEntity();
            voFruitList.add(voFruit);
        }
        return voFruitList;
    }


}
