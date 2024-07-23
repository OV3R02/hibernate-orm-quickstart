package comm.primeurad.arches.library.ports;

import comm.primeurad.arches.library.application.vo.VOAuthorBook;

public interface AuthorService {


    VOAuthorBook create (VOAuthorBook voAuthorBook);
    VOAuthorBook getAll ();
    VOAuthorBook getSingle (VOAuthorBook voAuthorBook);
    VOAuthorBook update (VOAuthorBook voAuthorBook);
    VOAuthorBook delete (VOAuthorBook voAuthorBook);

}
