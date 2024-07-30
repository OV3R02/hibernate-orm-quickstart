package comm.primeurad.arches.library.application.mapper;

import comm.primeurad.arches.library.application.vo.VOAuthor;
import comm.primeurad.arches.library.application.vo.VOBook;
import comm.primeurad.arches.library.domain.dto.EntAuthor;
import comm.primeurad.arches.library.domain.dto.EntBook;

import java.util.ArrayList;
import java.util.List;

public class VOAuthorMapper {

    private final VOAuthor voAuthor;

    public VOAuthorMapper(EntAuthor entAuthor, EntBook entBook) {
        this.voAuthor = converted(entAuthor, entBook);
    }

    public VOAuthor converted(EntAuthor entAuthor, EntBook entBook){
        if (entAuthor==null){
            throw new RuntimeException("No entAuthor found on mapper!");
        }
        VOAuthor voAuthor = new VOAuthor();
        if (entBook!=null) {
            List<VOBook> voBookList = new ArrayList<>();
            VOBookMapper voBookMapper = new VOBookMapper(entBook, entAuthor);
            voBookList.add(voBookMapper.getEntity());
            voAuthor.setVoBookList(voBookList);
        }
        voAuthor.setIdAuthor(IntegerConverter.convertFromIntegerToString(entAuthor.getId()));
        voAuthor.setName(entAuthor.getName());
        voAuthor.setSurname(entAuthor.getSurname());
        voAuthor.setDay(""+entAuthor.getBirthdate().getDayOfMonth());
        voAuthor.setMonth(""+entAuthor.getBirthdate().getMonth());
        voAuthor.setYear(""+entAuthor.getBirthdate().getYear());
        voAuthor.setEmail(entAuthor.getMail());
        voAuthor.setPhoneNumber(entAuthor.getNumber());
        voAuthor.setSex(entAuthor.getSex());


        return voAuthor;
    }

    public VOAuthor getEntity(){ return this.voAuthor; }

}
