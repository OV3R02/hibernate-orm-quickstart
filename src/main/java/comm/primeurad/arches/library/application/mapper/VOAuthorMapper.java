package comm.primeurad.arches.library.application.mapper;

import comm.primeurad.arches.library.application.vo.VOAuthor;
import comm.primeurad.arches.library.domain.dto.EntAuthor;

public class VOAuthorMapper {

    private VOAuthor voAuthor;

    public VOAuthorMapper(EntAuthor entAuthor) {
        this.voAuthor = converted(entAuthor);
    }

    public VOAuthor converted(EntAuthor entAuthor){
        VOAuthor voAuthor1 = new VOAuthor();

        return voAuthor1;
    }

}
