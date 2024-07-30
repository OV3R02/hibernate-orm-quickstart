package com.primeur.arches.application.mapper;

import com.primeur.arches.domain.dto.EntFruit;
import com.primeur.arches.application.vo.VOFruit;

public class VOFruitMapper {

    private VOFruit voFruit;

    public VOFruitMapper(EntFruit entFruit) {
        this.voFruit = convertVO(entFruit);
    }

    private VOFruit convertVO(EntFruit entFruit) {
        VOFruit newVOFruit = new VOFruit();
        newVOFruit.setName(entFruit.getName());
        newVOFruit.setId(""+entFruit.getId());
        newVOFruit.setDescription("description of "+entFruit.getName());
        return newVOFruit;
    }


    public VOFruit getEntity() {
        return this.voFruit;
    }
}
