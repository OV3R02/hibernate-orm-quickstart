package com.primeur.arches.ports;

import com.primeur.arches.application.vo.VOFruit;
import com.primeur.arches.domain.dto.EntFruit;

public interface FruitService {
    VOFruit create(VOFruit voFruit);
}
