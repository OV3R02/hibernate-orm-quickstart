package comm.primeurad.arches.library.application.mapper;

import comm.primeurad.arches.library.application.vo.VOAuthor;
import comm.primeurad.arches.library.application.vo.VOBook;
import comm.primeurad.arches.library.domain.dto.EntBook;

public class VOBookMapper {

    private final VOBook voAuthorBook;

    public VOBookMapper(EntBook entBook) {

        this.voAuthorBook = converted(entBook);
    }

    public VOBook converted(EntBook entBook){

        if (entBook==null){
            throw new RuntimeException("No entBook found on mapper!");
        }

        VOBook voBook = new VOBook();
        voBook.setId(IntegerConverter.convertFromIntegerToString(entBook.getId()));
        voBook.setTitle(entBook.getTitle());
        voBook.setPubHouse(entBook.getPubHouse());
        voBook.setPrice(entBook.getPrice());
        VOAuthor voAuthor = new VOAuthorMapper(entBook.getEntAuthor()).getEntity();
        voBook.setVoAuthor(voAuthor);
        return voBook;
    }

    public VOBook getEntity(){ return this.voAuthorBook; }

}

