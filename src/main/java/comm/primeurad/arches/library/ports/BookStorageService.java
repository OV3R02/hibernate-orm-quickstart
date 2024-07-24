package comm.primeurad.arches.library.ports;

import comm.primeurad.arches.library.domain.dto.EntBook;

import java.util.List;

public interface BookStorageService {

    EntBook create (EntBook entBook);
    List<EntBook> getAll ();
    EntBook getSingle (Integer id);
    EntBook update(EntBook entBook, Integer id);
    void delete(Integer id);
}
