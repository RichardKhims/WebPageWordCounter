package khims.richard.wpwc.reader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class UriReader implements SourceReader {
    private String uri;

    public UriReader(String uri) {
        this.uri = uri;
    }

    @Override
    public InputStream read() throws IOException {
        URL url = new URL(uri);
        return url.openStream();
    }
}
