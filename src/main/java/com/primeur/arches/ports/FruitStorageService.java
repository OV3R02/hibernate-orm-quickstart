package com.primeur.arches.ports;

import com.primeur.arches.domain.dto.EntFruit;

import java.util.List;

public interface FruitStorageService {

    EntFruit create (EntFruit fruit);
    List<EntFruit> get();
}
