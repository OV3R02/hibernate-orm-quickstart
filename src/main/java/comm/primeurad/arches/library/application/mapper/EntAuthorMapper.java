package comm.primeurad.arches.library.application.mapper;

import comm.primeurad.arches.library.application.vo.VOAuthor;
import comm.primeurad.arches.library.domain.dto.EntAuthor;

import java.time.LocalDate;

public class EntAuthorMapper {

    private final EntAuthor entAuthor;

    public EntAuthorMapper(VOAuthor voAuthorBook) {
        this.entAuthor = converted(voAuthorBook);
    }

    public EntAuthor converted(VOAuthor voAuthorBook){
        EntAuthor entAuthor1 = new EntAuthor();
        entAuthor1.setName(voAuthorBook.getName());
        entAuthor1.setSurname(voAuthorBook.getSurname());
        entAuthor1.setBirthdate(LocalDate.of(
                Integer.parseInt(voAuthorBook.getYear()),
                Integer.parseInt(voAuthorBook.getMonth()),
                Integer.parseInt(voAuthorBook.getDay())));
        entAuthor1.setMail(voAuthorBook.getEmail());
        entAuthor1.setSex(voAuthorBook.getSex());
        return entAuthor1;
    }

    public EntAuthor getEntity(){ return this.entAuthor; }

}
