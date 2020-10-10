package khims.richard.wpwc;

import khims.richard.wpwc.consumer.WordCounter;
import khims.richard.wpwc.handler.sax.BaseHandler;
import khims.richard.wpwc.parser.SaxParser;
import khims.richard.wpwc.parser.SourceParser;
import khims.richard.wpwc.reader.SourceReader;
import khims.richard.wpwc.reader.UriReader;

import java.util.Arrays;

public class Program {
    public static void main(String[] args) throws Exception {
        SourceReader uriReader = new UriReader(args[0]);

        WordCounter wordCounter = new WordCounter();
        BaseHandler handler = new BaseHandler(wordCounter);
        handler.setTagsToSkip(Arrays.asList("script", "noscript", "style", "meta", "html", "link", "img", "head", "iframe", "svg", "g", "header", "path", "section", "body", "html", "form", "main", "iblock", "upload", "footer", "input"));

        SourceParser parser = new SaxParser(handler);
        parser.parse(uriReader.read());

        System.out.println(wordCounter.getMapWords());
    }
}
