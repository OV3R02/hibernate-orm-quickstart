package com.primeur.arches.ports;

import com.primeur.arches.application.vo.VOFruit;
import com.primeur.arches.domain.dto.EntFruit;

import java.util.List;

public interface FruitService {
    VOFruit create(VOFruit voFruit);
    List<VOFruit> get();
}
