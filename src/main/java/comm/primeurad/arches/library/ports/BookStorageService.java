package comm.primeurad.arches.library.ports;

import comm.primeurad.arches.library.domain.dto.EntAuthor;
import comm.primeurad.arches.library.domain.dto.EntBook;

import java.util.List;
import java.util.Optional;

public interface BookStorageService {

    EntBook create (EntBook entBook);
    List<EntBook> getAll ();
    EntBook getSingle (Integer id);
    EntBook update(EntBook entBook, Integer id);
    void delete(Integer id);

    List<EntBook> findAllBooksByAuthor(EntAuthor entAuthor);
}
