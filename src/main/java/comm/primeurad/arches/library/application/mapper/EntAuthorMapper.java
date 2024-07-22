package comm.primeurad.arches.library.application.mapper;

import comm.primeurad.arches.library.application.vo.VOAuthor;
import comm.primeurad.arches.library.domain.dto.EntAuthor;

public class EntAuthorMapper {

    private EntAuthor entAuthor;

    public EntAuthorMapper(VOAuthor voAuthor) {
        this.entAuthor = converted(voAuthor);
    }

    public EntAuthor converted(VOAuthor voAuthor){
        EntAuthor entAuthor1 = new EntAuthor();

        return entAuthor1;
    }
}
