package comm.primeurad.arches.library.application.mapper;

import comm.primeurad.arches.library.application.vo.VOAuthor;
import comm.primeurad.arches.library.application.vo.VOBook;
import comm.primeurad.arches.library.domain.dto.EntAuthor;
import comm.primeurad.arches.library.domain.dto.EntBook;

public class EntBookMapper {

    private final EntBook entBook;

    public EntBookMapper(VOBook voBook, EntAuthor entAuthor) {
        this.entBook = converted(voBook, entAuthor);
    }
//    public EntBookMapper(VOBook voBook) { this.entBook = converted(voBook, entAuthor); }

    public EntBook converted(VOBook voBook, EntAuthor entAuthor){
//    public EntBook converted(VOBook voBook){

        EntBook entBook = new EntBook();
//        EntAuthorMapper entAuthorMapper = new EntAuthorMapper(voAuthor);
//        entBook.setEntAuthor(entAuthorMapper.getEntity());
        entBook.setEntAuthor(entAuthor);
        entBook.setTitle(voBook.getTitle());
        entBook.setPubHouse(voBook.getPubHouse());
        entBook.setType(voBook.getType());
        entBook.setPrice(voBook.getPrice());
        return entBook;
    }

   public EntBook getEntity() { return this.entBook; }

}