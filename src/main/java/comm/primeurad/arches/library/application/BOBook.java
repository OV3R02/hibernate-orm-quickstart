package comm.primeurad.arches.library.application;

import comm.primeurad.arches.library.application.mapper.EntBookMapper;
import comm.primeurad.arches.library.application.mapper.StringConverter;
import comm.primeurad.arches.library.application.mapper.VOAuthorMapper;
import comm.primeurad.arches.library.application.mapper.VOBookMapper;
import comm.primeurad.arches.library.application.vo.VOAuthor;
import comm.primeurad.arches.library.application.vo.VOBook;
import comm.primeurad.arches.library.domain.dto.EntAuthor;
import comm.primeurad.arches.library.domain.dto.EntBook;
import comm.primeurad.arches.library.ports.AuthorStorageService;
import comm.primeurad.arches.library.ports.BookService;
import comm.primeurad.arches.library.ports.BookStorageService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BOBook implements BookService {

    @Inject
    BookStorageService bookStorageService;

    @Inject
    AuthorStorageService authorStorageService;

    @Override
    public VOBook create(VOBook voBook, String id) {
        EntAuthor entAuthor = authorStorageService.getSingle(StringConverter.convertFromStringToInteger(id));
        EntBook entBook = getEntBook(voBook, entAuthor);
//        entBook.setEntAuthor(entAuthor);
        EntBook saved = bookStorageService.create(entBook);
        VOBookMapper voBookMapper = new VOBookMapper(saved);
        return voBookMapper.getEntity();
    }

    private static EntBook getEntBook(VOBook voBook, EntAuthor entAuthor) {
        if (voBook.getId() != null) {
            throw new BOBookException("idBook must be null!");
        }
        if (voBook.getTitle() == null || voBook.getTitle().isBlank()) {
            throw new BOBookException("Book title must not be blank!");
        }
        int i = voBook.getPrice().compareTo(BigDecimal.ZERO);
        if (i<=0) {
            throw new BOBookException("Price must be more of 0, not equals or less.");
        }

        EntBookMapper entBookMapper = new EntBookMapper(voBook, entAuthor);
        return entBookMapper.getEntity();
    }

    @Override
    public List<VOBook> getAll() {
        List<EntBook> allBooks = bookStorageService.getAll();
        List<VOBook> voBooksList = new ArrayList<>();

        for (EntBook entBook : allBooks) {
            VOBookMapper voBookMapper = new VOBookMapper(entBook);
            voBooksList.add(voBookMapper.getEntity());
        }
        return voBooksList;
    }

    @Override
    public VOBook getSingle(String id) {
        EntBook singleBook = bookStorageService.getSingle(
                StringConverter.convertFromStringToInteger(id));
        VOBookMapper voBookMapper = new VOBookMapper(singleBook);
        return  voBookMapper.getEntity();
    }

    @Override
    public VOBook update(VOBook voBook, String id) {
        EntBook foundBook = bookStorageService.getSingle(StringConverter.convertFromStringToInteger(id));
        if (foundBook==null){
            throw new BOBookException("No book found with id "+id);
        }
        EntBook entBookMapper = new EntBookMapper(voBook, foundBook.getEntAuthor()).getEntity();
        EntBook update = bookStorageService.update(
                entBookMapper,
                StringConverter.convertFromStringToInteger(id)
        );
        return new VOBookMapper(update).getEntity();
    }

    @Override
    public void delete(String id) {
        bookStorageService.delete(StringConverter.convertFromStringToInteger(id));
    }

    @Override
    public List<VOBook> getVOBooksByVOAuthorId(String id){
        if (id == null || id.isBlank()){
            throw new BOBookException("Id must be more of 0, can't be null");
        }

        EntAuthor single = authorStorageService
                .getSingle(StringConverter.convertFromStringToInteger(id));

        if (single==null){
            throw new BOBookException("Author not found!");
        }

        List<EntBook> allBooksByAuthor = bookStorageService
                .findAllBooksByAuthor(single);

        if (allBooksByAuthor.isEmpty()){
            return null;
        }

        List<VOBook> allVOBooksByAuthor = new ArrayList<>();
        for (EntBook entBook : allBooksByAuthor) {
            VOBook voBook = new VOBookMapper(entBook).getEntity();
            voBook.setVoAuthor(null);
            allVOBooksByAuthor.add(voBook);
        }

        return allVOBooksByAuthor;
    }
}
