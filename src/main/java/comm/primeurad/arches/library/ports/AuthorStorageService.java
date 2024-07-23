package comm.primeurad.arches.library.ports;

import comm.primeurad.arches.library.domain.dto.EntAuthor;

public interface AuthorStorageService {

    EntAuthor create (EntAuthor entAuthor);
    EntAuthor getAll ();
    EntAuthor getSingle (EntAuthor entAuthor);
    EntAuthor update (EntAuthor entAuthor);
    EntAuthor delete (EntAuthor entAuthor);

}
