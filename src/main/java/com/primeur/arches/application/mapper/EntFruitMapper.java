package com.primeur.arches.application.mapper;

import com.primeur.arches.application.vo.VOFruit;
import com.primeur.arches.domain.dto.EntFruit;

public class EntFruitMapper {

    EntFruit entFruit;

    public EntFruitMapper(VOFruit voFruit) {
        this.entFruit= convertEntity(voFruit);
    }

    private EntFruit convertEntity(VOFruit voFruit) {
        EntFruit fruit = new EntFruit();
        entFruit.setName(fruit.getName());
        return fruit;
    }

    public EntFruit getEntity() {
        return this.entFruit;
    }
}
