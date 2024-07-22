package comm.primeurad.arches.library.application.mapper;

import comm.primeurad.arches.library.application.vo.VOBook;
import comm.primeurad.arches.library.domain.dto.EntBook;

import java.math.BigDecimal;

public class VOBookMapper {

    public VOBookMapper(EntBook entBook) {
        VOBook voBook = converted(entBook);
    }

    public VOBook converted(EntBook entBook){
        VOBook voBook1 = new VOBook();
        voBook1.setId(String.valueOf(BigDecimal.valueOf(entBook.getId())));
        voBook1.setTitle(entBook.getTitle());
        voBook1.setPub_house(entBook.getPubHouse());
        voBook1.setType(entBook.getType());
        voBook1.setPrice(entBook.getPrice());
        voBook1.setVoAuthor(entBook.getEntAuthor().toString());
        return voBook1;
    }
}
