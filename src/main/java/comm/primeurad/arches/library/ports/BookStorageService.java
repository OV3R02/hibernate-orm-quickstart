package comm.primeurad.arches.library.ports;

import comm.primeurad.arches.library.domain.dto.EntBook;

public interface BookStorageService {

    EntBook create (EntBook entBook);
    EntBook getAll ();
    EntBook getSingle (EntBook entBook);
    EntBook update (EntBook entBook);
    EntBook delete (EntBook entBook);


}
