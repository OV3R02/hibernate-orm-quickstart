package com.primeur.test.arches;

import com.primeur.arches.application.BOFruit;
import com.primeur.arches.application.BOFruitException;
import com.primeur.arches.application.vo.VOFruit;
import com.primeur.arches.domain.dto.EntFruit;
import com.primeur.arches.ports.FruitStorageService;
import org.hibernate.HibernateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.smallrye.common.constraint.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BOFruitTest {

    @InjectMocks
    BOFruit boFruit;

    @Mock
    FruitStorageService fruitStorageService;

    //private EntFruit ent;
    private VOFruit vo;
    private EntFruit ent;
    private Optional<EntFruit> entFruitOptional;

    @BeforeEach
    void setUp(){

        this.vo = new VOFruit();
        vo.setId("1");
        vo.setName("Banana");
        vo.setDescription("This is a banana. Yummy!");
        vo.setDate(Date.valueOf("1950-12-31"));

        this.ent = new EntFruit();
        ent.setId(1);
        ent.setName("Banana");
    }

    @Test
    public void givenVOFruit_whenCreateFromBOFruit_thenOnlyCreateOneTimeFromFruitService(){

        ent.setId(null);
        when(fruitStorageService.create(Mockito.any())).thenReturn(ent);

        vo.setId(null);
        boFruit.create(vo);

        verify(fruitStorageService, times(1)).create(Mockito.any());
        verify(fruitStorageService, times(0)).getSingle(Mockito.any());
        verify(fruitStorageService, times(0)).get();
        verify(fruitStorageService, times(0)).delete(Mockito.any());
        verify(fruitStorageService, times(0)).update(Mockito.any(), Mockito.any());
    }

    @Test
    public void givenVOFruit_whenCreateFromBOFruit_thenReturnVOFruitByCreateFromFruitService(){
        ent.setId(null);
        when(fruitStorageService.create(Mockito.any())).thenReturn(ent);

        vo.setId(null);
        VOFruit voFruit = boFruit.create(vo);
        assertEquals("Banana", voFruit.getName());
    }

    @Test
    public void givenVOFruitWihIdNotNull_whenCreateFromBOFruit_thenReturnBOFruitException() {
        assertThrows(BOFruitException.class, () -> boFruit.create(vo));
    }

    @Test
    public void givenVOFruitWihNameNull_whenCreateFromBOFruit_thenReturnBOFruitException() {
        ent.setId(null);
        ent.setName(null);
        vo.setId(null);
        vo.setName(null);
        lenient().when(fruitStorageService.create(ent)).thenThrow(new HibernateException("Fruit's name must not be null!"));
        assertThrows(BOFruitException.class, () -> boFruit.create(vo));
    }

    @Test
    public void givenVOFruitWihEmptyName_whenCreateFromBOFruit_thenReturnBOFruitException() {
        ent.setId(null);
        ent.setName("    ");
        vo.setId(null);
        vo.setName("     ");
        lenient().when(fruitStorageService.create(ent)).thenThrow(new HibernateException("Fruit's name must not be null!"));
        assertThrows(BOFruitException.class, () -> boFruit.create(vo));
    }

    @Test
    public void givenVOFruitWihNameBlank_whenCreateFromBOFruit_thenReturnBOFruitException() {
        ent.setId(null);
        ent.setName("");
        vo.setId(null);
        vo.setName("");
        lenient().when(fruitStorageService.create(ent)).thenThrow(new HibernateException("Fruit's Id must be null!"));
        assertThrows(BOFruitException.class, () -> boFruit.create(vo));
    }

    @Test
    public void givenVOFruit_whenGetSingle_thenOnlyReturnOneTimeFromFruitStorageService() {

        EntFruit entFruit = new EntFruit();
        entFruit.setName("Banana");
        Integer id = 1;
        entFruit.setId(id);
        entFruitOptional = Optional.of(entFruit);
        when(fruitStorageService.getSingle(id)).thenReturn(entFruitOptional);

        boFruit.getSingle(String.valueOf(id));

        verify(fruitStorageService, times(1)).getSingle(Mockito.any());
        verify(fruitStorageService, times(0)).create(Mockito.any());
        verify(fruitStorageService, times(0)).get();
        verify(fruitStorageService, times(0)).delete(Mockito.any());
        verify(fruitStorageService, times(0)).update(Mockito.any(), Mockito.any());

    }

    @Test
    public void givenVOFruitId_whenGetSingle_thenReturnFruitVO() {

        EntFruit entFruit = new EntFruit();
        entFruit.setName("Banana");
        Integer id = 1;
        entFruit.setId(id);

        entFruitOptional = Optional.of(entFruit);
        when(fruitStorageService.getSingle(id)).thenReturn(entFruitOptional);
        VOFruit single = boFruit.getSingle(String.valueOf(id)).orElseThrow();

        assertNotNull(single);
        assertEquals("Banana", single.getName());
        assertEquals(""+id, single.getId());
    }

    @Test
    public void givenNullVOFruitId_whenGetSingle_thenReturnBOFruitException() {
        assertThrows(BOFruitException.class, () -> boFruit.getSingle(null));
    }

    @Test
    public void givenBlankVOFruitId_whenGetSingle_thenReturnBOFruitException() {
        assertThrows(BOFruitException.class, () -> boFruit.getSingle("  "));
    }

    @Test
    public void givenStringPlusBlankSpacesVOFruitId_whenGetSingle_thenReturnEmptyOptional() {
        assertEquals(Optional.empty(), boFruit.getSingle("2"));
    }

    @Test
    public void whenGetAllFromBOFruit_thenOnlyGetAllOneTimeFromFruitService(){

        boFruit.get();
        verify(fruitStorageService, times(1)).get();
        verify(fruitStorageService, times(0)).getSingle(Mockito.any());
        verify(fruitStorageService, times(0)).create(Mockito.any());
        verify(fruitStorageService, times(0)).delete(Mockito.any());
        verify(fruitStorageService, times(0)).update(Mockito.any(), Mockito.any());
    }

    @Test
    public void whenGetFromBOFruit_thenReturnFruitVOList() {

        List<EntFruit> entFruitList = new ArrayList<>();
        entFruitList.add(ent);
        when(fruitStorageService.get()).thenReturn(entFruitList);
        List<VOFruit> voFruitList = boFruit.get();
        assertFalse(voFruitList.isEmpty());
    }

    @Test
    public void whenUpdateFromBOFruit_thenOnlyUpdateOneTimeFromFruitService(){

        when(fruitStorageService.update(Mockito.any(), Mockito.any())).thenReturn(ent);

        VOFruit newVOFruit = new VOFruit();
        newVOFruit.setName("Melon");
        newVOFruit.setDescription("This is a melon. Slurp!");
        boFruit.update(newVOFruit, "1");

        verify(fruitStorageService, times(1)).update(Mockito.any(), Mockito.any());
        verify(fruitStorageService, times(0)).get();
        verify(fruitStorageService, times(0)).getSingle(Mockito.any());
        verify(fruitStorageService, times(0)).create(Mockito.any());
        verify(fruitStorageService, times(0)).delete(Mockito.any());
    }

    @Test
    public void givenNullVOFruitId_whenUpdateFromBOFruit_thenReturnBOFruitException() {
        assertThrows(BOFruitException.class, () -> boFruit.update(vo, null));
    }

    @Test
    public void givenBlankVOFruitId_whenUpdateFromBOFruit_thenReturnBOFruitException() {
        assertThrows(BOFruitException.class, () -> boFruit.update(vo, "   "));
    }


    @Test
    public void whenDeleteFromBOFruit_thenOnlyDeleteOneTimeFromFruitService(){

        entFruitOptional = Optional.of(ent);
        when(fruitStorageService.getSingle(1)).thenReturn(entFruitOptional);
        boFruit.delete("1");

        verify(fruitStorageService, times(1)).delete(Mockito.any());
        verify(fruitStorageService, times(1)).getSingle(Mockito.any());
        verify(fruitStorageService, times(0)).update(Mockito.any(), Mockito.any());
        verify(fruitStorageService, times(0)).get();
        verify(fruitStorageService, times(0)).create(Mockito.any());
    }

    @Test
    public void givenNullVOFruitId_whenDeleteFromBOFruit_thenReturnBOFruitException() {
        assertThrows(BOFruitException.class, () -> boFruit.delete(null));
    }

    @Test
    public void givenBlankVOFruitId_whenDeleteFromBOFruit_thenReturnBOFruitException() {
        assertThrows(BOFruitException.class, () -> boFruit.delete("   "));
    }


}
