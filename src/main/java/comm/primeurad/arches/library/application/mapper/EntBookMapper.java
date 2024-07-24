package comm.primeurad.arches.library.application.mapper;

import comm.primeurad.arches.library.application.vo.VOAuthorBook;
import comm.primeurad.arches.library.domain.dto.EntBook;

public class EntBookMapper {

    private final EntBook entBook;

    public EntBookMapper(VOAuthorBook voBook) {
        this.entBook = converted(voBook);
    }

    public EntBook converted(VOAuthorBook voBook){
        EntBook entBook = new EntBook();
        entBook.setTitle(voBook.getTitle());
        entBook.setPubHouse(voBook.getPub_house());
        entBook.setType(voBook.getType());
        entBook.setPrice(voBook.getPrice());
        entBook.setEntAuthor();
        //entBook.setEntAuthor(entAuthorMapper.getEntity());
        return entBook;
    }

   public EntBook getEntity() { return this.entBook; }

}