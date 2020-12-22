package data;

import java.io.IOException;
import java.util.List;

public interface FileSource<T> {
    void toFile(List<T> list) throws IOException;
    List<T> fromFile() throws IOException;
}
