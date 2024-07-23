package comm.primeurad.arches.library.application.mapper;

import comm.primeurad.arches.library.application.vo.VOAuthorBook;
import comm.primeurad.arches.library.domain.dto.EntBook;

public class EntBookMapper {

    private final EntBook entBook;

    public EntBookMapper(VOAuthorBook voAuthorBook) {
        this.entBook = converted(voAuthorBook);
    }

    public EntBook converted(VOAuthorBook voAuthorBook){
        EntBook entBook = new EntBook();
        EntAuthorMapper entAuthorMapper = new EntAuthorMapper(voAuthorBook);
        entBook.setId(Integer.valueOf(voAuthorBook.getIdBook()));
        entBook.setTitle(voAuthorBook.getTitle());
        entBook.setPubHouse(voAuthorBook.getPub_house());
        entBook.setType(voAuthorBook.getType());
        entBook.setPrice(voAuthorBook.getPrice());
        entBook.setEntAuthor(entAuthorMapper.getEntity());
        return entBook;
    }

   public EntBook getEntity() { return this.entBook; }

}