package comm.primeurad.arches.library.ports;

import comm.primeurad.arches.library.application.vo.VOAuthor;
import comm.primeurad.arches.library.application.vo.VOBook;

import java.util.List;

public interface BookService {

    VOBook create (VOBook voBook, String id);
    List<VOBook> getAll ();
    VOBook getSingle (String id);
    VOBook update (VOBook voAuthorBook, String id);
    void delete (String id);

    List<VOBook> getVOBooksByVOAuthorId(String id);
}
