package comm.primeurad.arches.library.application.mapper;

import comm.primeurad.arches.library.application.vo.VOAuthor;
import comm.primeurad.arches.library.application.vo.VOBook;
import comm.primeurad.arches.library.domain.dto.EntAuthor;
import comm.primeurad.arches.library.domain.dto.EntBook;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EntAuthorMapper {

    private final EntAuthor entAuthor;

    public EntAuthorMapper(VOAuthor voAuthor) {
        this.entAuthor = converted(voAuthor);
    }

    public EntAuthor converted(VOAuthor voAuthor){

        EntAuthor entAuthor = new EntAuthor();
        entAuthor.setName(voAuthor.getName());
        entAuthor.setSurname(voAuthor.getSurname());

        LocalDate localDate = LocalDate.of(
                Integer.parseInt(voAuthor.getYear()),
                Integer.parseInt(voAuthor.getMonth()),
                Integer.parseInt(voAuthor.getDay()));

        entAuthor.setBirthdate(localDate);
        entAuthor.setMail(voAuthor.getEmail());
        entAuthor.setSex(voAuthor.getSex());
        entAuthor.setNumber(voAuthor.getPhoneNumber());

        return entAuthor;
    }

    public EntAuthor getEntity(){ return this.entAuthor; }

}
