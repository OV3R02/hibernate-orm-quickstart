package comm.primeurad.arches.library.application.mapper;

import comm.primeurad.arches.library.application.vo.VOAuthor;
import comm.primeurad.arches.library.application.vo.VOBook;
import comm.primeurad.arches.library.domain.dto.EntAuthor;
import comm.primeurad.arches.library.domain.dto.EntBook;

import java.util.ArrayList;
import java.util.List;

public class VOBookMapper {

    private final VOBook voAuthorBook;

    public VOBookMapper(EntBook entBook, EntAuthor entAuthor) {

        this.voAuthorBook = converted(entBook, entAuthor);
    }

    public VOBook converted(EntBook entBook, EntAuthor entAuthor){

        List<VOAuthor> voAuthorList = new ArrayList<>();
        VOAuthorMapper voAuthorMapper = new VOAuthorMapper(entAuthor, entBook);
        voAuthorList.add(voAuthorMapper.getEntity());


        if (entBook==null){
            throw new RuntimeException("No entBook found on mapper!");
        }
        if (entAuthor==null){
             throw new RuntimeException("No author found for book on mapper");
        }
        VOBook voAuthorBook = new VOBook();
        voAuthorBook.setIdBook(IntegerConverter.convertFromIntegerToString(entBook.getId()));
        voAuthorBook.setTitle(entBook.getTitle());
        voAuthorBook.setPub_house(entBook.getPubHouse());
        voAuthorBook.setPrice(entBook.getPrice());
        //voAuthorBook.setIdAuthor(String.valueOf(entBook.getEntAuthor().getId()));
        return voAuthorBook;
    }

    public VOBook getEntity(){ return this.voAuthorBook; }

}

