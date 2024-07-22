package comm.primeurad.arches.library.application.mapper;

import comm.primeurad.arches.library.application.vo.VOBook;
import comm.primeurad.arches.library.domain.dto.EntBook;

import java.math.BigDecimal;

public class EntBookMapper {

    public EntBookMapper(VOBook voBook) {
        EntBook entBook = converted(voBook);
    }

    public EntBook converted(VOBook voBook){
        EntBook entBook = new EntBook();
        entBook.setId(Integer.valueOf(voBook.getId()));
        entBook.setTitle(voBook.getTitle());
        entBook.setPubHouse(voBook.getPub_house());
        entBook.setType(voBook.getType());
        entBook.setPrice(voBook.getPrice());
        entBook.setEntAuthor(voBook.getVoAuthor());
        return entBook;
    }

}