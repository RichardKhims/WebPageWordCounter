package khims.richard.wpwc.reader;

import java.io.IOException;
import java.io.InputStream;

public interface SourceReader {
    InputStream read() throws IOException;
}
