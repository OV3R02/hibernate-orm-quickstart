package comm.primeurad.arches.library.application;

import comm.primeurad.arches.library.application.mapper.EntAuthorMapper;
import comm.primeurad.arches.library.application.mapper.StringConverter;
import comm.primeurad.arches.library.application.mapper.VOAuthorMapper;
import comm.primeurad.arches.library.application.vo.VOAuthor;
import comm.primeurad.arches.library.domain.dto.EntAuthor;
import comm.primeurad.arches.library.ports.AuthorService;
import comm.primeurad.arches.library.ports.AuthorStorageService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BOAuthor implements AuthorService {

    @Inject
    AuthorStorageService authorStorageService;

    @Override
    public VOAuthor create(VOAuthor voAuthorBook) {
        EntAuthorMapper entAuthorMapper = new EntAuthorMapper(voAuthorBook);
        EntAuthor entAuthor = entAuthorMapper.getEntity();
        authorStorageService.create(entAuthor);
        VOAuthorMapper voAuthorMapper = new VOAuthorMapper(entAuthor);
        return voAuthorMapper.getEntity();
    }

    @Override
    public List<VOAuthor> getAll() {
        List<EntAuthor> allBooks = authorStorageService.getAll();
        List<VOAuthor> voBooksList = new ArrayList<>();

        for (int i = 0; i < allBooks.size(); i++) {
            EntAuthor EntAuthor = allBooks.get(i);
            VOAuthorMapper voAuthorMapper= new VOAuthorMapper(EntAuthor);
            voBooksList.add(voAuthorMapper.getEntity());
        }
        return voBooksList;
    }

    @Override
    public VOAuthor getSingle(String id) {
        EntAuthor singleAuthor = authorStorageService.getSingle(
                StringConverter.convertFromStringToInteger(id));
        VOAuthorMapper voAuthorMapper = new VOAuthorMapper(singleAuthor);
        return  voAuthorMapper.getEntity();
    }

    @Override
    public VOAuthor update(VOAuthor voAuthorBook, String id) {
        EntAuthorMapper EntAuthorMapper = new EntAuthorMapper(voAuthorBook);
        EntAuthor update = authorStorageService.update(
                EntAuthorMapper.getEntity(),
                StringConverter.convertFromStringToInteger(id));
        VOAuthorMapper voAuthorMapper = new VOAuthorMapper(update);
        return voAuthorMapper.getEntity();
    }

    @Override
    public void delete(String id) {
        authorStorageService.delete(StringConverter.convertFromStringToInteger(id));
    }
}
