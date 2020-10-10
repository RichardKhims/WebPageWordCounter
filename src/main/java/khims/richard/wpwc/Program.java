package khims.richard.wpwc;

import khims.richard.wpwc.consumer.WordCounter;
import khims.richard.wpwc.handler.SaxHandler;
import khims.richard.wpwc.parser.SaxParser;
import khims.richard.wpwc.parser.SourceParser;
import khims.richard.wpwc.parser.StreamLineParser;
import khims.richard.wpwc.reader.SourceReader;
import khims.richard.wpwc.reader.UriReader;

import java.util.Arrays;

public class Program {
    public static void main(String[] args) throws Exception {
//        runSaxParser(args[0]);
        runLineStreamParser(args[0]);
    }

    private static void runLineStreamParser(String uri) throws Exception {
        SourceReader uriReader = new UriReader(uri);
        WordCounter wordCounter = new WordCounter();
        SourceParser parser = new StreamLineParser(wordCounter);
        parser.parse(uriReader.read());

        System.out.println(wordCounter);
    }

    private static void runSaxParser(String uri) throws Exception {
        SourceReader uriReader = new UriReader(uri);

        WordCounter wordCounter = new WordCounter();
        SaxHandler handler = new SaxHandler(wordCounter);
        handler.setTagsToSkip(Arrays.asList("script", "noscript", "style", "meta", "html", "link", "img", "head", "iframe", "svg", "g", "header", "path", "section", "body", "html", "form", "main", "iblock", "upload", "footer", "input"));

        SourceParser parser = new SaxParser(handler);
        parser.parse(uriReader.read());

        System.out.println(wordCounter);
    }
}
