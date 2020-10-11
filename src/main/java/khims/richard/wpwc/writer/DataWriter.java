package khims.richard.wpwc.writer;

import java.io.InputStream;

public interface DataWriter {
    void write(InputStream input) throws Exception;
}
