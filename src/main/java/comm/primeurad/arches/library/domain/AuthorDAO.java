package comm.primeurad.arches.library.domain;

import comm.primeurad.arches.library.domain.dto.BOAuthorException;
import comm.primeurad.arches.library.domain.dto.DAOBookException;
import comm.primeurad.arches.library.domain.dto.EntAuthor;
import comm.primeurad.arches.library.ports.AuthorStorageService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class AuthorDAO implements AuthorStorageService {

    @Inject
    EntityManager entityManager;

    @Override
    public EntAuthor create(EntAuthor entAuthor) {
        if (entAuthor.getId()!=null){
            throw new BOAuthorException("Author with id "+entAuthor.getId()+" is invalidly set on request");
        }
        entityManager.persist(entAuthor);
        return entAuthor;
    }

    @Override
    public List<EntAuthor> getAll() {
        return entityManager.createNamedQuery("Authors.findAll", EntAuthor.class).getResultList();
    }

    @Override
    public EntAuthor getSingle(Integer id) {
        return entityManager.find(EntAuthor.class, id);
    }

    @Override
    public EntAuthor update(EntAuthor entAuthor, Integer id) {

        EntAuthor newEntAuthor = entityManager.find(EntAuthor.class, id);
        if (newEntAuthor==null) {
            throw new DAOBookException("Author not found!");
        }
        newEntAuthor.setName(entAuthor.getName());
        newEntAuthor.setSurname(entAuthor.getSurname());
        newEntAuthor.setBirthdate(entAuthor.getBirthdate());
        newEntAuthor.setSex(entAuthor.getSex());
        newEntAuthor.setMail(entAuthor.getMail());
        newEntAuthor.setNumber(entAuthor.getNumber());
        return newEntAuthor;
    }

    @Override
    public void delete(Integer id) {
        EntAuthor entity = entityManager.getReference(EntAuthor.class, id);
        if (entity.getId()==null){
            throw new RuntimeException("Author with id "+entity.getId()+" not found!");
        }
        entityManager.remove(entity);
        System.out.println("Author successfully removed!");
    }
}
