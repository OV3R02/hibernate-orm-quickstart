package com.primeur.test.arches;

import com.primeur.arches.adapter.rest.FruitResource;
import com.primeur.arches.application.BOFruitException;
import com.primeur.arches.application.vo.VOFruit;
import com.primeur.arches.ports.FruitService;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
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
import java.util.stream.IntStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FruitResourceTest {

    @InjectMocks
    FruitResource fruitResource;

    @Mock
    FruitService fruitService;

    private VOFruit vo;
    private VOFruit newVo;
    private String id = "1";
    private Optional<VOFruit> voFruitOptional;

    private List<VOFruit> voFruitList;

    @BeforeEach
    void setUp(){
        this.vo = new VOFruit();
        vo.setId("1");
        vo.setName("Banana");
        vo.setDescription("This is a banana. Yummy!");
        vo.setDate(Date.valueOf("1950-12-31"));

        this.voFruitList = new ArrayList<>();
        IntStream.range(0, 10).forEach(i -> {
            vo.setId(String.valueOf(i));
            vo.setName("banana " + i);
            voFruitList.add(vo);
        });

        this.newVo = new VOFruit();
        newVo.setId("1");
        newVo.setName("Cucumber");
        newVo.setDescription("This is a Cucumber. Yummy!");
        newVo.setDate(Date.valueOf("1950-12-31"));
    }



    // Test for create method on REST



    // First test, method must be executed only one time
    @Test
    public void givenVoFruit_whenCreateFromFruitResource_thenOnlyCreateShouldBeCalledOnceFromService() throws BOFruitException{
        vo.setId(null);
        when(fruitService.create(vo)).thenReturn(vo);
        fruitResource.create(vo);
        Mockito.verify(fruitService, Mockito.times(1)).create(vo);
        Mockito.verify(fruitService, Mockito.times(0)).getSingle(Mockito.any());
        Mockito.verify(fruitService, Mockito.times(0)).get();
        Mockito.verify(fruitService, Mockito.times(0)).delete(Mockito.any());
        Mockito.verify(fruitService, Mockito.times(0)).update(Mockito.any(), Mockito.any());
    }

    // Second test, method must return Response 200
    @Test
    public void givenVOFruit_whenCreate_thenReturnResponse200() throws BOFruitException {
        vo.setId(null);
        vo.setName("Melon");
        when(fruitService.create(vo)).thenReturn(vo);
        Response response = fruitResource.create(vo);

        assertEquals(201, response.getStatus());
        assertEquals("Melon", ((VOFruit) response.getEntity()).getName());
    }

    // Third test, method must return a WebApplicationException
    @Test
    public void givenVOFruitWithIdNotNull_whenGetSingleFromFruitResource_thenReturnWebApplicationException(){
        when(fruitService.create(vo)).thenThrow(new BOFruitException("VOFruit id must be null!"));
        WebApplicationException webAppEx = assertThrows(WebApplicationException.class, () -> fruitResource.create(vo));
        assertEquals(422, webAppEx.getResponse().getStatus());
    }



    // Test for getSingle method of REST



    // First test, method must be executed only one time
    @Test
    public void whenGetSingleFromFruitResource_thenOnlyFindByIdentifierShouldBeCalledOnceFromService(){
        voFruitOptional = Optional.of(vo);
        when(fruitService.getSingle("1")).thenReturn(voFruitOptional);
        fruitResource.getSingle("1");

        Mockito.verify(fruitService, Mockito.times(1)).getSingle("1");
        Mockito.verify(fruitService, Mockito.times(0)).get();
        Mockito.verify(fruitService, Mockito.times(0)).create(Mockito.any());
        Mockito.verify(fruitService, Mockito.times(0)).delete(Mockito.any());
        Mockito.verify(fruitService, Mockito.times(0)).update(Mockito.any(), Mockito.any());
    }

    // Second test, method must return Response 200
    @Test
    public void givenFruitId_whenGetSingle_thenReturnResponse200() throws BOFruitException {
        vo.setName("Melon");
        voFruitOptional = Optional.of(vo);
        when(fruitService.getSingle("1")).thenReturn(voFruitOptional);
        Response response = fruitResource.getSingle("1");

        assertEquals(200, response.getStatus());
        assertEquals("Melon", ((VOFruit) response.getEntity()).getName());
    }

    // Third test, method must return a WebApplicationException with Status code
    @Test
    public void givenNonExistentVOFruit_whenGetSingleFromFruitResource_thenReturnWebApplicationException(){
        voFruitOptional = Optional.empty();
        when(fruitService.getSingle("2")).thenReturn(voFruitOptional);
        WebApplicationException webAppEx = assertThrows(WebApplicationException.class, () -> fruitResource.getSingle("2"));
        assertEquals(404, webAppEx.getResponse().getStatus());
    }


    // Test for get method of REST



    // First test, method must be executed only one time
    @Test
    public void whenGetFromFruitResource_thenGetShouldBeCalledOnceFromService(){
        when(fruitService.get()).thenReturn(voFruitList);
        fruitResource.get();

        Mockito.verify(fruitService, Mockito.times(1)).get();
        Mockito.verify(fruitService, Mockito.times(0)).getSingle(Mockito.any());
        Mockito.verify(fruitService, Mockito.times(0)).create(Mockito.any());
        Mockito.verify(fruitService, Mockito.times(0)).delete(Mockito.any());
        Mockito.verify(fruitService, Mockito.times(0)).update(Mockito.any(), Mockito.any());
    }

    // Second test, method must return Response 200
    @Test
    public void whenGetFromResource_thenReturnResponse200() throws BOFruitException {
        when(fruitService.get()).thenReturn(voFruitList);
        Response response = fruitResource.get();
        assertEquals(200, response.getStatus());
    }

    // Third test, method must return a WebApplicationException with 204 code
    @Test
    public void whenGetFromFruitResourceIsEmpty_thenReturnWebApplicationException204(){
        when(fruitService.get()).thenThrow(new BOFruitException("List is empty!"));
        WebApplicationException webAppEx = assertThrows(WebApplicationException.class, () -> fruitResource.get());
        assertEquals(204, webAppEx.getResponse().getStatus());
    }



    // Test for update method of REST



    // First test, method must be executed only one time
    @Test
    public void givenVOFruitAndId_whenUpdateFromFruitResource_thenUpdateShouldBeCalledOnceFromService(){
        when(fruitService.update(newVo, id)).thenReturn(newVo);
        fruitResource.update(id, newVo);

        Mockito.verify(fruitService, Mockito.times(1)).update(newVo, id);
        Mockito.verify(fruitService, Mockito.times(0)).get();
        Mockito.verify(fruitService, Mockito.times(0)).getSingle(Mockito.any());
        Mockito.verify(fruitService, Mockito.times(0)).create(Mockito.any());
        Mockito.verify(fruitService, Mockito.times(0)).delete(Mockito.any());
    }

    // Second test, method must return Response 200
    @Test
    public void givenVOFruitAndId_whenUpdateFromResource_thenReturnResponse200() throws BOFruitException {
        when(fruitService.update(newVo, id)).thenReturn(newVo);
        Response response = fruitResource.update(id, newVo);
        assertEquals(200, response.getStatus());
    }

    // First part of third test, method must return a WebApplicationException with 422 code
    @Test
    public void givenVOFruitWithNullId_whenUpdateFromFruitResourceIsEmpty_thenReturnWebApplicationException422(){

        lenient().when(fruitService.update(newVo,null)).thenThrow(new BOFruitException("Id was invalidly set on request."));
        WebApplicationException webAppEx = assertThrows(WebApplicationException.class, () -> fruitResource.update(null, newVo));
        assertEquals(422, webAppEx.getResponse().getStatus());
    }

   // Second part of third test, method must return a WebApplicationException with 422 code
    @Test
    public void givenVOFruitWithBlankId_whenUpdateFromFruitResourceIsEmpty_thenReturnWebApplicationException422(){

        lenient().when(fruitService.update(newVo,"")).thenThrow(new BOFruitException("Id was invalidly set on request."));
        WebApplicationException webAppEx = assertThrows(WebApplicationException.class, () -> fruitResource.update("", newVo));
        assertEquals(422, webAppEx.getResponse().getStatus());
    }

    // Third part of third test, method must return a WebApplicationException with 422 code
    @Test
    public void givenVOFruitWithNullName_whenUpdateFromFruitResourceIsEmpty_thenReturnWebApplicationException422(){
        newVo.setName(null);
        lenient().when(fruitService.update(newVo,id)).thenThrow(new BOFruitException("Name was invalidly set on request."));
        WebApplicationException webAppEx = assertThrows(WebApplicationException.class, () -> fruitResource.update(id, newVo));
        assertEquals(422, webAppEx.getResponse().getStatus());
    }

    // Third part of third test, method must return a WebApplicationException with 422 code
    @Test
    public void givenVOFruitWithBlankName_whenUpdateFromFruitResourceIsEmpty_thenReturnWebApplicationException422(){
        newVo.setName("");
        lenient().when(fruitService.update(newVo,id)).thenThrow(new BOFruitException("Name was invalidly set on request."));
        WebApplicationException webAppEx = assertThrows(WebApplicationException.class, () -> fruitResource.update(id, newVo));
        assertEquals(422, webAppEx.getResponse().getStatus());
    }


    // Test for update method of REST



    // First test, method must be executed only one time
    @Test
    public void givenVOFruitId_whenDeleteFromFruitResource_thenDeleteShouldBeCalledOnceFromService(){
        //when(fruitService.delete(id)).thenReturn(vo);
        fruitResource.delete(id);
        Mockito.verify(fruitService, Mockito.times(1)).delete(id);
        Mockito.verify(fruitService, Mockito.times(0)).update(Mockito.any(), Mockito.any());
        Mockito.verify(fruitService, Mockito.times(0)).get();
        Mockito.verify(fruitService, Mockito.times(0)).getSingle(Mockito.any());
        Mockito.verify(fruitService, Mockito.times(0)).create(Mockito.any());
    }

    // Second test, method must return Response 200
    @Test
    public void givenVOFruitId_whenDeleteFromResource_thenReturnResponse200() throws BOFruitException {
        // when(fruitService.delete(id)).thenReturn(vo);
        Response response = fruitResource.delete(id);
        assertEquals(204, response.getStatus());
    }

    // First part of third test, method must return a WebApplicationException with 422 code
    @Test
    public void givenVOFruitWithNullId_whenDeleteFromFruitResourceIsEmpty_thenReturnWebApplicationException422(){

        //lenient().when(fruitService.delete(null)).thenThrow(new BOFruitException("Id was invalidly set on request."));
        WebApplicationException webAppEx = assertThrows(WebApplicationException.class, () -> fruitResource.delete(null));
        assertEquals(422, webAppEx.getResponse().getStatus());
    }

    // Second part of third test, method must return a WebApplicationException with 422 code
    @Test
    public void givenVOFruitWithBlankId_whenDeleteFromFruitResourceIsEmpty_thenReturnWebApplicationException422(){

        //lenient().when(fruitService.delete("")).thenThrow(new BOFruitException("Id was invalidly set on request."));
        WebApplicationException webAppEx = assertThrows(WebApplicationException.class, () -> fruitResource.delete(""));
        assertEquals(422, webAppEx.getResponse().getStatus());
    }

    @Test
    public void givenVOFruitWithNotValidId_whenDeleteFromFruitResourceIsEmpty_thenReturnWebApplicationException422(){
        doThrow(new BOFruitException("bla bla")).when(fruitService).delete("404");
        WebApplicationException webAppEx = assertThrows(WebApplicationException.class, () -> fruitResource.delete("404"));
        assertEquals(404, webAppEx.getResponse().getStatus());
    }

}
