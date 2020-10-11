package khims.richard.wpwc;

import khims.richard.wpwc.consumer.WordCounter;
import khims.richard.wpwc.handler.SaxHandler;
import khims.richard.wpwc.parser.SaxParser;
import khims.richard.wpwc.parser.SourceParser;
import khims.richard.wpwc.parser.StreamLineParser;
import khims.richard.wpwc.reader.FileReader;
import khims.richard.wpwc.reader.SourceReader;
import khims.richard.wpwc.reader.UriReader;
import khims.richard.wpwc.writer.DataWriter;
import khims.richard.wpwc.writer.FileWriter;

import java.io.InputStream;
import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Program {
    private static final Logger log = LogManager.getLogger(Program.class);

    public static void main(String[] args) {
        if (args.length < 1) {
            log.warn("No URI argument given");
            return;
        }

        log.info("Start program with argument: " + args[0]);
//        runSaxParser(args[0]);
        runLineStreamParser(args[0]);
    }

    private static void runLineStreamParser(String uri) {
        try {
            SourceReader reader = new UriReader(uri);
            WordCounter wordCounter = new WordCounter();
            SourceParser parser = new StreamLineParser(wordCounter);

            log.info("Reading html from uri: " + uri);
            InputStream read = reader.read();

            log.info("Writing into file");
            DataWriter writer = new FileWriter("out.html");
            writer.write(read);
            read.close();

            log.info("Reading html from file");
            reader = new FileReader("out.html");
            read = reader.read();

            log.info("Start parsing html");
            parser.parse(read);
            log.info("Finished parsing html");
            read.close();

            log.info("Result:\n" + wordCounter);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private static void runSaxParser(String uri) throws Exception {
        SourceReader uriReader = new UriReader(uri);

        WordCounter wordCounter = new WordCounter();
        SaxHandler handler = new SaxHandler(wordCounter);
        handler.setTagsToSkip(Arrays.asList("script", "noscript", "style", "meta", "html", "link", "img", "head", "iframe", "svg", "g", "header", "path", "section", "body", "html", "form", "main", "iblock", "upload", "footer", "input"));

        SourceParser parser = new SaxParser(handler);
        InputStream read = uriReader.read();
        parser.parse(read);
        read.close();

        System.out.println(wordCounter);
    }
}
