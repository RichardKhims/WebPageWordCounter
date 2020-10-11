package khims.richard.wpwc.reader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileReader implements SourceReader {
    private String filename;

    public FileReader(String filename) {
        this.filename = filename;
    }

    @Override
    public InputStream read() throws IOException {
        return new FileInputStream(filename);
    }
}
