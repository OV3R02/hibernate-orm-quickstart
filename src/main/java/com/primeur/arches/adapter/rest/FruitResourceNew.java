package com.primeur.arches.adapter.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.primeur.arches.application.BOFruit;
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
import static io.quarkus.arc.impl.UncaughtExceptions.LOGGER;

@Path("fruits")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class FruitResourceNew {

    @Inject
    FruitService fruitService;

    @POST
    @Transactional
    public Response create(VOFruit voFruit) {

        if (voFruit.getId() != null) {
            throw new WebApplicationException("Id was invalidly set on request. ", 422);
        }
        VOFruit voFruitSaved;
        voFruitSaved = fruitService.create(voFruit);
        return Response.ok(voFruitSaved).status(201).build();
    }

    @GET
    public List<VOFruit> get() {
        return fruitService.get();
    }

    @GET
    @Path("{id}")
    public VOFruit getSingle(String id) {
        VOFruit voFruit = fruitService.getSingle(id);
        if (voFruit.getId() == null) {
            throw new WebApplicationException("Fruit with id of " + id + " does not exist.", 404);
        }
        return voFruit;
    }

    @PUT
    @Path("{id}")
    @Transactional
    public VOFruit update(String id, VOFruit voFruit) {


        if (voFruit.getName() == null) {
            throw new WebApplicationException("Fruit Name was not set on request.", 422);
        }
        VOFruit newVoFruit = fruitService.update(voFruit, id);
        if (newVoFruit == null) {
            throw new WebApplicationException("Fruit with id of " + id + " does not exist.", 404);
        }
        return newVoFruit;
    }


    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(String id) {
        VOFruit voFruit = fruitService.delete(id);
        if (voFruit == null) {
            throw new WebApplicationException("Fruit with id of " + id + " does not exist.", 404);
        }
        return Response.status(204).build();
    }


    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {

        @Inject
        ObjectMapper objectMapper;

        @Override
        public Response toResponse(Exception exception) {
            LOGGER.error("Failed to handle request", exception);

            int code = 500;
            if (exception instanceof WebApplicationException) {
                code = ((WebApplicationException) exception).getResponse().getStatus();
            }

            ObjectNode exceptionJson = objectMapper.createObjectNode();
            exceptionJson.put("exceptionType", exception.getClass().getName());
            exceptionJson.put("code", code);

            if (exception.getMessage() != null) {
                exceptionJson.put("error", exception.getMessage());
            }

            return Response.status(code)
                    .entity(exceptionJson)
                    .build();
        }

    }
}
