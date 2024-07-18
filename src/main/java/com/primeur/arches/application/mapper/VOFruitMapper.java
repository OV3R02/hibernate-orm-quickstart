package com.primeur.arches.application.mapper;

import com.primeur.arches.application.vo.VOFruit;
import com.primeur.arches.domain.dto.EntFruit;

public class VOFruitMapper {

    VOFruit voFruit;

    public VOFruitMapper(EntFruit entFruit) {
        this.voFruit = convertVO(entFruit);
    }

    private VOFruit convertVO(EntFruit entFruit) {
        VOFruit voFruit1 = new VOFruit();
        voFruit.setName(entFruit.getName());
        return  voFruit1;
    }


    public VOFruit getEntity() {
        return this.voFruit;
    }
}
