package khims.richard.wpwc.parser;

import java.io.InputStream;

public interface SourceParser {
    void parse(InputStream input) throws Exception;
}
