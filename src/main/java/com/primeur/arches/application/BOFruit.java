package com.primeur.arches.application;

import com.primeur.arches.application.mapper.EntFruitMapper;
import com.primeur.arches.application.mapper.VOFruitMapper;
import com.primeur.arches.application.vo.VOFruit;
import com.primeur.arches.domain.dto.EntFruit;
import com.primeur.arches.ports.FruitService;
import com.primeur.arches.ports.FruitStorageService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@ApplicationScoped
public class BOFruit implements FruitService {

    @Inject
    FruitStorageService fruitStorageService;

    @Override
    public VOFruit create(VOFruit voFruit) {

        if (voFruit.getId() != null) {
            throw new BOFruitException("voFruit id must be null.");
        }
        if (voFruit.getName() == null || voFruit.getName().isBlank()) {
            throw new BOFruitException("Invalid name for fruit. Name must not be null, empty or blank");
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
    public Optional<VOFruit> getSingle(String id) {
        if (id == null || id.isBlank()){
            throw new BOFruitException("Id must not be empty, blank or null");
        }
        Optional<EntFruit> single = fruitStorageService.getSingle(getIdFromString(id));
        return single.isPresent() ? single.map(b -> new VOFruitMapper(b).getEntity()) : Optional.empty();
    }

    @Override
    public VOFruit update(VOFruit voFruit, String id) {
        if (id == null || id.isBlank()){
            throw new BOFruitException("Invalid id for Fruit, mus not be blank, empty or null");
        }
        EntFruitMapper entFruitMapper = new EntFruitMapper(voFruit);
        EntFruit entFruit = entFruitMapper.getEntity();
        EntFruit newEntFruit = fruitStorageService.update(entFruit, getIdFromString(id));
        VOFruitMapper voFruitMapper = new VOFruitMapper(newEntFruit);
        return voFruitMapper.getEntity();
    }

    @Override
    public void delete(String id) {
        if (id == null || id.isBlank()) {
            throw new BOFruitException("Id must not be blank, empty or null");
        }
        EntFruit entFruitFound = fruitStorageService.getSingle(getIdFromString(id))
                .orElseThrow(() -> new BOFruitException("No fruit found with id: "+id));
        fruitStorageService.delete(entFruitFound);
    }

    public int getIdFromString(String id){
        return Integer.parseInt(id);
    }
}
