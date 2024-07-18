package com.primeur.arches.application.mapper;

import com.primeur.arches.application.vo.VOFruit;
import com.primeur.arches.domain.dto.EntFruit;
import jakarta.enterprise.context.RequestScoped;

import java.util.ArrayList;
import java.util.List;

public class VOFruitMapper {

    private VOFruit voFruit;

    public VOFruitMapper(EntFruit entFruit) {
        this.voFruit = convertVO(entFruit);
    }

    private VOFruit convertVO(EntFruit entFruit) {
        VOFruit voFruit1 = new VOFruit();
        voFruit1.setName(entFruit.getName());
        voFruit1.setId(""+entFruit.getId());
        voFruit1.setDescription("description of "+entFruit.getName());
        return voFruit1;
    }


    public VOFruit getEntity() {
        return this.voFruit;
    }
}
