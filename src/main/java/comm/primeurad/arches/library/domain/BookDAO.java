package comm.primeurad.arches.library.domain;

import comm.primeurad.arches.library.application.BOBookException;
import comm.primeurad.arches.library.domain.dto.DAOBookException;
import comm.primeurad.arches.library.domain.dto.EntAuthor;
import comm.primeurad.arches.library.domain.dto.EntBook;
import comm.primeurad.arches.library.ports.BookStorageService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class BookDAO implements BookStorageService {

    @Inject
    EntityManager entityManager;

    @Override
    public EntBook create(EntBook entBook) {
        if (entBook.getId()!=null){
            throw new BOBookException("Book "+entBook.getTitle()+" already exists!");
        }
        entityManager.persist(entBook);
        return entBook;
    }

    @Override
    public List<EntBook> getAll() {
        return entityManager.createNamedQuery("Books.findAll", EntBook.class).getResultList();
    }

    @Override
    public EntBook getSingle(Integer id) {
        return entityManager.find(EntBook.class, id);
    }

    @Override
    public EntBook update(EntBook entBook, Integer id) {

        EntBook newEntBook = entityManager.find(EntBook.class, id);

        if (newEntBook==null) {
            throw new DAOBookException("Book not found!");
        }
        newEntBook.setTitle(entBook.getTitle());
        newEntBook.setPrice(entBook.getPrice());
        newEntBook.setEntAuthor(entBook.getEntAuthor());
        newEntBook.setType(entBook.getType());

        return newEntBook;
    }

    @Override
    public void delete(Integer id) {
        EntBook entity = entityManager.getReference(EntBook.class, id);
        if (entity.getId()==null){
            throw new RuntimeException("Book with id "+entity.getId()+" not found!");
        }
        entityManager.remove(entity);
        System.out.println("Book successfully removed!");
    }

    @Override
    public List<EntBook> findAllBooksByAuthor(EntAuthor entAuthor){
        return  entityManager
                .createQuery("SELECT b FROM EntBook b WHERE b.entAuthor = :entAuthor", EntBook.class)
                .setParameter("entAuthor", entAuthor).getResultList();
    }

}

