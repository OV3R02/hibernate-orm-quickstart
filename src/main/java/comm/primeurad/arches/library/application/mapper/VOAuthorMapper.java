package comm.primeurad.arches.library.application.mapper;

import comm.primeurad.arches.library.application.vo.VOAuthor;
import comm.primeurad.arches.library.application.vo.VOBook;
import comm.primeurad.arches.library.domain.dto.EntAuthor;
import comm.primeurad.arches.library.ports.BookService;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

public class VOAuthorMapper {

//    @Inject
//    BookService bookService;

    private final VOAuthor voAuthor;

    public VOAuthorMapper(EntAuthor entAuthor) {
        this.voAuthor = converted(entAuthor);
    }

    public VOAuthor converted(EntAuthor entAuthor){
        if (entAuthor==null){
            throw new RuntimeException("No entAuthor found on mapper!");
        }
        VOAuthor voAuthor = new VOAuthor();
        voAuthor.setId(IntegerConverter.convertFromIntegerToString(entAuthor.getId()));
        voAuthor.setName(entAuthor.getName());
        voAuthor.setSurname(entAuthor.getSurname());
        voAuthor.setDay(""+entAuthor.getBirthdate().getDayOfMonth());
        voAuthor.setMonth(""+entAuthor.getBirthdate().getMonthValue());
        voAuthor.setYear(""+entAuthor.getBirthdate().getYear());
        voAuthor.setEmail(entAuthor.getMail());
        voAuthor.setPhoneNumber(entAuthor.getNumber());
        voAuthor.setSex(entAuthor.getSex());

//        List<VOBook> voBookList = bookService.getVOBooksByVOAuthorId(voAuthor.getId());
//        voAuthor.setVoBookList(voBookList);

        return voAuthor;
    }

    public VOAuthor getEntity(){ return this.voAuthor; }

}
