package comm.primeurad.arches.library.application;

import comm.primeurad.arches.library.application.mapper.EntAuthorMapper;
import comm.primeurad.arches.library.application.mapper.StringConverter;
import comm.primeurad.arches.library.application.mapper.VOAuthorMapper;
import comm.primeurad.arches.library.application.vo.VOAuthor;
import comm.primeurad.arches.library.application.vo.VOBook;
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

    @Inject
    BOBook boBook;

    @Override
    public VOAuthor create(VOAuthor voAuthor) {
        EntAuthorMapper entAuthorMapper = new EntAuthorMapper(voAuthor);
        EntAuthor entAuthor = entAuthorMapper.getEntity();
        authorStorageService.create(entAuthor);
        return new VOAuthorMapper(entAuthor).getEntity();
    }

    @Override
    public List<VOAuthor> getAll() {
        List<EntAuthor> allBooks = authorStorageService.getAll();
        List<VOAuthor> voAuthors = new ArrayList<>();

        for (comm.primeurad.arches.library.domain.dto.EntAuthor EntAuthor : allBooks) {
            VOAuthorMapper voAuthorMapper = new VOAuthorMapper(EntAuthor);
            VOAuthor voAuthor = voAuthorMapper.getEntity();
            List<VOBook> voBookList = boBook.getVOBooksByVOAuthorId(voAuthor.getId());
            voAuthor.setVoBookList(voBookList);
            voAuthors.add(voAuthor);
        }
        return voAuthors;
    }

    @Override
    public VOAuthor getSingle(String id) {
        EntAuthor singleAuthor = authorStorageService.getSingle(
                StringConverter.convertFromStringToInteger(id));

        VOAuthorMapper voAuthorMapper = new VOAuthorMapper(singleAuthor);

        List<VOBook> voBookList = boBook.getVOBooksByVOAuthorId(id);
        voAuthorMapper.getEntity().setVoBookList(voBookList);
        return  voAuthorMapper.getEntity();
    }

    @Override
    public VOAuthor update(VOAuthor voAuthor, String id) {
        EntAuthorMapper entAuthorMapper = new EntAuthorMapper(voAuthor);
        EntAuthor update = authorStorageService.update(
                entAuthorMapper.getEntity(),
                StringConverter.convertFromStringToInteger(id));
        VOAuthorMapper voAuthorMapper = new VOAuthorMapper(update);
        return voAuthorMapper.getEntity();
    }

    @Override
    public void delete(String id) {
        authorStorageService.delete(StringConverter.convertFromStringToInteger(id));
    }
}
