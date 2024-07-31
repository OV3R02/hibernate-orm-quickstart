package comm.primeurad.arches.library.adapter.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import comm.primeurad.arches.library.application.vo.VOBook;
import comm.primeurad.arches.library.ports.AuthorService;
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

    @Inject
    AuthorService authorService;

    @POST
    @Path("{id}")
    @Transactional
    public Response createBook(VOBook voBook, String id) {

        if (voBook.getId() != null) {
            throw new WebApplicationException("Id was invalidly set on request. ", 422);
        }
        VOBook voBookSaved = bookService.create(voBook, id);
        return Response.ok(voBookSaved).status(201).build();
    }



    @GET
    public List<VOBook> get() {
        return bookService.getAll();
    }

    @GET
    @Path("{id}")
    public VOBook getSingle(String id) {
        VOBook voAuthorBook = bookService.getSingle(id);
        if (voAuthorBook.getId() == null) {
            throw new WebApplicationException("Book with id of " + id + " does not exist.", 404);
        }
        return voAuthorBook;
    }

    @PUT
    @Path("{id}")
    @Transactional
    public VOBook update(String id, VOBook voAuthorBook) {


        if (voAuthorBook.getTitle() == null) {
            throw new WebApplicationException("Book Name was not set on request.", 422);
        }
        VOBook newVoBook = bookService.update(voAuthorBook, id);
        if (newVoBook == null) {
            throw new WebApplicationException("Book with id of " + id + " does not exist.", 404);
        }
        return newVoBook;
    }


    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(String id) {
        bookService.delete(id);
        VOBook single = bookService.getSingle(id);
        if (single == null) {
            throw new WebApplicationException("Book with id of " + id + " does not exist.", 404);
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
