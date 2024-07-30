package com.primeur.arches.adapter.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.primeur.arches.application.BOFruitException;
import com.primeur.arches.application.vo.VOFruit;
import com.primeur.arches.ports.FruitService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.List;
import java.util.Optional;

import static io.quarkus.arc.impl.UncaughtExceptions.LOGGER;


@Path("fruits")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class FruitResource {

    @Inject
    FruitService fruitService;

    @POST
    @Transactional
    public Response create(VOFruit voFruit) {
        VOFruit voFruitSaved;
        try {
            voFruitSaved = fruitService.create(voFruit);
        } catch (BOFruitException be){
            throw new WebApplicationException("Id was invalidly set on request. ", 422);
        }
        return Response.ok(voFruitSaved).status(201).build();
    }

    @GET
    public Response get() {
        List<VOFruit> voFruits;
        try {
           voFruits = fruitService.get();
        } catch (BOFruitException be) {
            throw new WebApplicationException("List is empty.", 204);
        }
        return Response.ok(voFruits).status(200).build();
    }

    @GET
    @Path("{id}")
    public Response getSingle(String id) {
        VOFruit voFruit;
            voFruit = fruitService.getSingle(id)
                    .orElseThrow(() -> new WebApplicationException("Fruit with id of " + id + " does not exist.", 404));
        return Response.ok(voFruit).status(200).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(String id, VOFruit voFruit) {
        if (id == null || id.isBlank()) {
            throw new WebApplicationException("Fruit id must not be null or blank.", 422);
        }
        if (voFruit.getName() == null || voFruit.getName().isBlank()) {
            throw new WebApplicationException("Fruit Name wasn't set on request.", 422);
        }
        VOFruit newVOFruit = fruitService.update(voFruit, id);
        return Response.ok(newVOFruit).status(200).build();

    }


    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(String id) {
        if (id == null || id.isBlank()){
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }
        try {
            fruitService.delete(id);
        } catch (BOFruitException be){
            throw new WebApplicationException("Failed to delete fruit, invalid id", 404);
        }
        return Response.status(204).build();
    }
}
