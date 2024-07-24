package comm.primeurad.arches.library.adapter.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import comm.primeurad.arches.library.application.vo.VOAuthorBook;
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
    public Response createAuthor(VOAuthorBook voAuthor) {

        if (voAuthor.getIdAuthor() != null) {
            throw new WebApplicationException("Id was invalidly set on request. ", 422);
        }
        VOAuthorBook voAuthorSaved = authorService.create(voAuthor);
        return Response.ok(voAuthorSaved).status(201).build();
    }
    
    

    @GET
    public List<VOAuthorBook> get() {
        return authorService.getAll();
    }

    @GET
    @Path("{id}")
    public VOAuthorBook getSingle(String id) {
        VOAuthorBook voAuthor = authorService.getSingle(id);
        if (voAuthor == null) {
            throw new WebApplicationException("Author with id of " + id + " does not exist.", 404);
        }
        return voAuthor;
    }

    @PUT
    @Path("{id}")
    @Transactional
    public VOAuthorBook update(String id, VOAuthorBook voFruit) {


        if (voFruit.getName() == null) {
            throw new WebApplicationException("Author Name was not set on request.", 422);
        }
        VOAuthorBook newVoAuthor = authorService.update(voFruit, id);
        if (newVoAuthor == null) {
            throw new WebApplicationException("Fruit with id of " + id + " does not exist.", 404);
        }
        return newVoAuthor;
    }


    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(String id) {
        VOAuthorBook single = authorService.getSingle(id);
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
