package com.primeur.arches.ports;

import com.primeur.arches.domain.dto.EntFruit;

import java.util.List;

public interface FruitStorageService {

    EntFruit create (EntFruit fruit);
    List<EntFruit> get();
    EntFruit getSingle(Integer id);
    EntFruit delete (Integer id);
    EntFruit update (EntFruit entFruit, Integer id);
}
