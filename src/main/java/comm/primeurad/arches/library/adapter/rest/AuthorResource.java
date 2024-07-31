package comm.primeurad.arches.library.adapter.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import comm.primeurad.arches.library.application.vo.VOAuthor;
import comm.primeurad.arches.library.ports.AuthorService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.List;

import static io.quarkus.arc.impl.UncaughtExceptions.LOGGER;

@Path("author")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class AuthorResource {

    @Inject
    AuthorService authorService;

    @POST
    @Transactional
    public Response createAuthor(VOAuthor voAuthor) {

        if (voAuthor.getId() != null) {
            throw new WebApplicationException("Id was invalidly set on request. ", 422);
        }
        VOAuthor voAuthorSaved = authorService.create(voAuthor);
        return Response.ok(voAuthorSaved).status(201).build();
    }
    
    

    @GET
    public Response get() {
        List<VOAuthor> allAuthors = authorService.getAll();
        return Response.ok(allAuthors).status(200).build();
    }

    @GET
    @Path("{id}")
    public Response getSingle(String id) {
        VOAuthor voAuthor = authorService.getSingle(id);
        if (voAuthor == null) {
            throw new WebApplicationException("Author with id of " + id + " does not exist.", 404);
        }
        return Response.ok(voAuthor).status(200).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(String id, VOAuthor voFruit) {

        if (voFruit.getName() == null) {
            throw new WebApplicationException("Author Name was not set on request.", 422);
        }
        VOAuthor newVoAuthor = authorService.update(voFruit, id);
        if (newVoAuthor == null) {
            throw new WebApplicationException("Author must be more than 0, can't be null.", 500);
        }
        return Response.ok(newVoAuthor).status(200).build();
    }


    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(String id) {
        VOAuthor single = authorService.getSingle(id);
        if (single == null) {
            throw new WebApplicationException("Author with id " + id + " does not exist.", 404);
        }
        authorService.delete(id);
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
