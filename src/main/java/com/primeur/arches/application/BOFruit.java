package com.primeur.arches.application;

import com.primeur.arches.application.mapper.EntFruitMapper;
import com.primeur.arches.application.mapper.VOFruitMapper;
import com.primeur.arches.application.vo.VOFruit;
import com.primeur.arches.domain.dto.EntFruit;
import com.primeur.arches.ports.FruitService;
import com.primeur.arches.ports.FruitStorageService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BOFruit implements FruitService {

    @Inject
    FruitStorageService fruitStorageService;

    @Override
    public VOFruit create(VOFruit voFruit) {

        if (voFruit.getName() == "") {
            throw new BOFruitException("Invalid name for fruit. Must not be blank.");
        }
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

    @Override
    public VOFruit getSingle(String id) {
        EntFruit entFruit = fruitStorageService.getSingle(getIdFromString(id));
        VOFruitMapper voMapper = new VOFruitMapper(entFruit);
        return voMapper.getEntity();
    }

    @Override
    public VOFruit delete(String id) {
        EntFruit entFruit = fruitStorageService.delete(getIdFromString(id));
        VOFruitMapper voMapper = new VOFruitMapper(entFruit);
        return voMapper.getEntity();
    }

    @Override
    public VOFruit update(VOFruit voFruit, String id) {
        EntFruitMapper entFruitMapper = new EntFruitMapper(voFruit);
        EntFruit entFruit = fruitStorageService.update(entFruitMapper.getEntity(), getIdFromString(id));
        VOFruitMapper voFruitMapper = new VOFruitMapper(entFruit);
        return voFruitMapper.getEntity();
    }


    public int getIdFromString(String id){
        return Integer.parseInt(id);
    }

    public String getIdFromInt(int id){
        return String.valueOf(id);
    }
}
