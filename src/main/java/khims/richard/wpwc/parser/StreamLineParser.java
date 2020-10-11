package khims.richard.wpwc.parser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Consumer;

public class StreamLineParser implements SourceParser {
    private Consumer<String> consumer;

    public StreamLineParser(Consumer<String> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void parse(InputStream input) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line;
        while ((line = reader.readLine()) != null) {
            consumer.accept(line);
        }
    }
}
