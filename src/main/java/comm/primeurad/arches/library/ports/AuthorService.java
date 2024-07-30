package comm.primeurad.arches.library.ports;

import comm.primeurad.arches.library.application.vo.VOAuthor;

import java.util.List;

public interface AuthorService {


    VOAuthor create (VOAuthor voAuthorBook);
    List<VOAuthor> getAll ();
    VOAuthor getSingle (String id);
    VOAuthor update (VOAuthor voAuthorBook, String id);
    void delete (String id);

}
