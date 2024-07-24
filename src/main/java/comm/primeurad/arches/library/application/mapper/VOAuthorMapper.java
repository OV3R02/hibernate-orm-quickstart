package comm.primeurad.arches.library.application.mapper;

import comm.primeurad.arches.library.application.vo.VOAuthorBook;
import comm.primeurad.arches.library.domain.dto.EntAuthor;
import comm.primeurad.arches.library.domain.dto.EntBook;

public class VOAuthorMapper {

    private final VOAuthorBook voAuthorBook;

    public VOAuthorMapper(EntAuthor entAuthor) {
        this.voAuthorBook = converted(entAuthor);
    }

    public VOAuthorBook converted(EntAuthor entAuthor){
        if (entAuthor==null){
            throw new RuntimeException("No entAuthor found on mapper!");
        }
        VOAuthorBook voAuthorBook = new VOAuthorBook();
        voAuthorBook.setIdAuthor(IntegerConverter.convertFromIntegerToString(entAuthor.getId()));
        voAuthorBook.setName(entAuthor.getName());
        voAuthorBook.setSurname(entAuthor.getSurname());
        voAuthorBook.setDay(""+entAuthor.getBirthdate().getDayOfMonth());
        voAuthorBook.setMonth(""+entAuthor.getBirthdate().getMonth());
        voAuthorBook.setYear(""+entAuthor.getBirthdate().getYear());
        voAuthorBook.setEmail(entAuthor.getMail());
        voAuthorBook.setPhoneNumber(entAuthor.getNumber());
        voAuthorBook.setSex(entAuthor.getSex());
        return voAuthorBook;
    }

    public VOAuthorBook getEntity(){ return this.voAuthorBook; }

}
