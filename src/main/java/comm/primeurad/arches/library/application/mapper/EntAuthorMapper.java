package comm.primeurad.arches.library.application.mapper;

import comm.primeurad.arches.library.application.vo.VOAuthorBook;
import comm.primeurad.arches.library.domain.dto.EntAuthor;

import java.time.LocalDate;

public class EntAuthorMapper {

    private final EntAuthor entAuthor;

    public EntAuthorMapper(VOAuthorBook voAuthorBook) {
        this.entAuthor = converted(voAuthorBook);
    }

    public EntAuthor converted(VOAuthorBook voAuthorBook){
        EntAuthor entAuthor1 = new EntAuthor();
        entAuthor1.setId(Integer.valueOf(voAuthorBook.getIdBook()));
        entAuthor1.setName(voAuthorBook.getName());
        entAuthor1.setSurname(voAuthorBook.getSurname());
        entAuthor1.setBirthdate(LocalDate.of(
                Integer.valueOf(voAuthorBook.getYear()),
                Integer.valueOf(voAuthorBook.getMonth()),
                Integer.valueOf(voAuthorBook.getDay())));
        return entAuthor1;
    }

    public EntAuthor getEntity(){ return this.entAuthor; }
}
