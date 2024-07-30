package com.primeur.arches.ports;

import com.primeur.arches.domain.dto.EntFruit;

import java.util.List;
import java.util.Optional;

public interface FruitStorageService {

    EntFruit create (EntFruit fruit);
    List<EntFruit> get();
    Optional<EntFruit> getSingle(Integer id);
    //EntFruit delete (Integer id);

    void delete(EntFruit entFruit);

    EntFruit update (EntFruit entFruit, Integer id);
}
