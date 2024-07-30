package com.primeur.arches.ports;

import com.primeur.arches.application.vo.VOFruit;

import java.util.List;
import java.util.Optional;

public interface FruitService {
    VOFruit create(VOFruit voFruit);
    List<VOFruit> get();
    Optional<VOFruit> getSingle(String id);
    void delete (String id);
    VOFruit update (VOFruit voFruit, String id);
}
