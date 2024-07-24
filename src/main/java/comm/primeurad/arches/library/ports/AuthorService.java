package comm.primeurad.arches.library.ports;

import comm.primeurad.arches.library.application.vo.VOAuthorBook;

import java.util.List;

public interface AuthorService {


    VOAuthorBook create (VOAuthorBook voAuthorBook);
    List<VOAuthorBook> getAll ();
    VOAuthorBook getSingle (String id);
    VOAuthorBook update (VOAuthorBook voAuthorBook, String id);
    void delete (String id);

}
