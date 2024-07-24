package comm.primeurad.arches.library.application;

import comm.primeurad.arches.library.application.mapper.EntBookMapper;
import comm.primeurad.arches.library.application.mapper.StringConverter;
import comm.primeurad.arches.library.application.mapper.VOAuthorMapper;
import comm.primeurad.arches.library.application.mapper.VOBookMapper;
import comm.primeurad.arches.library.application.vo.VOAuthorBook;
import comm.primeurad.arches.library.domain.dto.EntBook;
import comm.primeurad.arches.library.ports.BookService;
import comm.primeurad.arches.library.ports.BookStorageService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BOBook implements BookService {

    @Inject
    BookStorageService bookStorageService;

    @Override
    public VOAuthorBook create(VOAuthorBook voBook) {
        if (voBook.getIdBook()!=null){
            throw new BOBookException("idBook must be null!");
        }
        if (voBook.getTitle()==null || voBook.getTitle().isBlank()){
            throw new BOBookException("Book title must not be blank!");
        }
        int i = voBook.getPrice().compareTo(BigDecimal.ZERO);
        if (i<=0) {
            throw new BOBookException("Price must be more of 0, not equals or less.");
        }


        EntBookMapper entBookMapper = new EntBookMapper(voBook);
        EntBook entBook = entBookMapper.getEntity();
        bookStorageService.create(entBook);
        VOBookMapper voBookMapper = new VOBookMapper(entBook);
        return voBookMapper.getEntity();
    }

    @Override
    public List<VOAuthorBook> getAll() {
        List<EntBook> allBooks = bookStorageService.getAll();
        List<VOAuthorBook> voBooksList = new ArrayList<>();

        for (EntBook entBook : allBooks) {
            VOBookMapper voBookMapper = new VOBookMapper(entBook);
            voBooksList.add(voBookMapper.getEntity());
        }
        return voBooksList;
    }

    @Override
    public VOAuthorBook getSingle(String id) {
        EntBook singleBook = bookStorageService.getSingle(
                StringConverter.convertFromStringToInteger(id));
        VOBookMapper voBookMapper = new VOBookMapper(singleBook);
        return  voBookMapper.getEntity();
    }

    @Override
    public VOAuthorBook update(VOAuthorBook voBook, String id) {
        EntBookMapper entBookMapper = new EntBookMapper(voBook);
        EntBook update = bookStorageService.update(
                entBookMapper.getEntity(),
                StringConverter.convertFromStringToInteger(id)
        );
        VOBookMapper voBookMapper = new VOBookMapper(update);
        return voBookMapper.getEntity();
    }

    @Override
    public void delete(String id) {
        bookStorageService.delete(StringConverter.convertFromStringToInteger(id));
    }
}
