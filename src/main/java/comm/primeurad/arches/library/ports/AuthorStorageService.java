package comm.primeurad.arches.library.ports;

import comm.primeurad.arches.library.domain.dto.EntAuthor;
import comm.primeurad.arches.library.domain.dto.EntBook;

import java.util.List;

public interface AuthorStorageService {

    EntAuthor create(EntAuthor EntAuthor);
    List<EntAuthor> getAll ();
    EntAuthor getSingle(Integer id);
    EntAuthor update(EntAuthor EntAuthor, Integer id);
    void delete(Integer id);
}
