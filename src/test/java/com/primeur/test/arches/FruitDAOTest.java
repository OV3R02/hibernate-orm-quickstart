package com.primeur.test.arches;

import com.primeur.arches.domain.dto.EntFruit;
import com.primeur.arches.domain.dto.FruitDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FruitDAOTest {

    @InjectMocks
    FruitDAO fruitDAO;

    @Mock
    EntityManager eman;

    @Mock
    TypedQuery<EntFruit> typedQuery;

    private EntFruit entFruit;


    @BeforeEach
    void setUp() {
        entFruit = new EntFruit();
        entFruit.setId(1);
        entFruit.setName("banana");
    }

    @Test
    public void givenEntFruit_whenCreateFromFruitDAO_thenPersistOnlyOneTimeFromEntityManager() {
        fruitDAO.create(entFruit);
        verify(eman, times(1)).persist(Mockito.any());
        verify(eman, only()).persist(Mockito.any());
    }

    @Test
    public void whenGetFromFruitDAO_thenFindAllOnlyOneTimeFromEntityManager() {
        when(eman.createNamedQuery("Fruits.findAll", EntFruit.class))
                .thenReturn(typedQuery);
        fruitDAO.get();
        verify(eman, times(1)).createNamedQuery("Fruits.findAll", EntFruit.class);
        verify(eman, only()).createNamedQuery("Fruits.findAll", EntFruit.class);
    }

    @Test
    public void givenEntFruit_whenUpdateFromFruitDAO_thenPersistOnlyOneTimeFromEntityManager() {

        entFruit.setName("melon");
        when(eman.find(EntFruit.class, 1)).thenReturn(entFruit);
        fruitDAO.update(entFruit, 1);
        verify(eman, times(1)).find(EntFruit.class, 1);
        verify(eman, times(1)).persist(entFruit);
    }

    @Test
    public void givenEntFruitId_whenGetSingleFromFruitDAO_thenGetSingleOnlyOneTimeFromEntityManager() {
        when(eman.find(EntFruit.class, 1)).thenReturn(entFruit);
        fruitDAO.getSingle(1);
        verify(eman, times(1)).find(Mockito.any(), Mockito.anyInt());
        verify(eman, only()).find(Mockito.any(), Mockito.anyInt());
    }

    @Test
    public void givenAbsentEntFruitId_whenGetSingleFromFruitDAO_thenReturnEmptyOptional() {
        when(eman.find(EntFruit.class, 404)).thenReturn(null);
        Optional<EntFruit> single = fruitDAO.getSingle(404);
        assertTrue(single.isEmpty());
    }


    @Test
    public void givenEntFruitId_whenDeleteFromFruitDAO_thenDeleteOnceFromEntityManager() {
        fruitDAO.delete(entFruit);
        verify(eman, times(1)).remove(entFruit);
        verify(eman, only()).remove(Mockito.any());

    }

}
