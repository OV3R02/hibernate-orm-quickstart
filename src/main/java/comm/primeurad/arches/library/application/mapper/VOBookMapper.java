package comm.primeurad.arches.library.application.mapper;

import comm.primeurad.arches.library.application.vo.VOAuthorBook;
import comm.primeurad.arches.library.domain.dto.EntBook;

public class VOBookMapper {

    private final VOAuthorBook voAuthorBook;

    public VOBookMapper(EntBook entBook) {
        this.voAuthorBook = converted(entBook);
    }

    public VOAuthorBook converted(EntBook entBook){
        VOAuthorBook voAuthorBook = new VOAuthorBook();
        voAuthorBook.setIdBook(String.valueOf(entBook.getId()));
        voAuthorBook.setTitle(entBook.getTitle());
        voAuthorBook.setPub_house(entBook.getPubHouse());
        voAuthorBook.setPrice(entBook.getPrice());
        voAuthorBook.setIdAuthor(String.valueOf(entBook.getEntAuthor().getId()));
        return voAuthorBook;
    }

    public VOAuthorBook getEntity(){ return this.voAuthorBook; }

}
