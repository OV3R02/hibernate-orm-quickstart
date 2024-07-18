package com.primeur.arches.adapter.rest;

import com.primeur.arches.application.vo.VOFruit;
import com.primeur.arches.ports.FruitService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import java.util.List;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
    public Response create (VOFruit voFruit){
        if (voFruit.getId() != null) {
            throw new WebApplicationException("Id was invalidly set on request. ", 422);
        }
        VOFruit voFruitSaved = fruitService.create(voFruit);
        return Response.ok(voFruitSaved).status(201).build();
    }

    @GET
    public List<VOFruit> get() {
        List<VOFruit> voFruitList = fruitService.get();
        return voFruitList;
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
