package comm.primeurad.arches.library.adapter.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import comm.primeurad.arches.library.application.vo.VOAuthorBook;
import comm.primeurad.arches.library.ports.BookService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.List;
import static io.quarkus.arc.impl.UncaughtExceptions.LOGGER;

@Path("books")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class BookResource {

    @Inject
    BookService bookService;

    @POST
    @Transactional
    public Response createBook(VOAuthorBook voBook) {

        if (voBook.getIdBook() != null) {
            throw new WebApplicationException("Id was invalidly set on request. ", 422);
        }
        VOAuthorBook voBookSaved;
        voBookSaved = bookService.create(voBook);
        return Response.ok(voBookSaved).status(201).build();
    }



    @GET
    public List<VOAuthorBook> get() {
        return bookService.getAll();
    }

    @GET
    @Path("{id}")
    public VOAuthorBook getSingle(String id) {
        VOAuthorBook voAuthorBook = bookService.getSingle(id);
        if (voAuthorBook.getIdBook() == null) {
            throw new WebApplicationException("Fruit with id of " + id + " does not exist.", 404);
        }
        return voAuthorBook;
    }

    @PUT
    @Path("{id}")
    @Transactional
    public VOAuthorBook update(String id, VOAuthorBook voAuthorBook) {


        if (voAuthorBook.getName() == null) {
            throw new WebApplicationException("Fruit Name was not set on request.", 422);
        }
        VOAuthorBook newVoBook = bookService.update(voAuthorBook, id);
        if (newVoBook == null) {
            throw new WebApplicationException("Fruit with id of " + id + " does not exist.", 404);
        }
        return newVoBook;
    }


    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(String id) {
        bookService.delete(id);
        VOAuthorBook single = bookService.getSingle(id);
        if (single == null) {
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
