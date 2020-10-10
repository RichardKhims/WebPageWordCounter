package khims.richard.wpwc;

import khims.richard.wpwc.consumer.WordCounter;
import khims.richard.wpwc.handler.BaseHandler;
import org.ccil.cowan.tagsoup.Parser;
import org.xml.sax.*;

import java.io.*;
import java.net.URL;
import java.util.Arrays;

public class Program {
    public static void main(String[] args) throws IOException, SAXException {
        URL url = new URL(args[0]);
        XMLReader xmlReader = new Parser();
        xmlReader.setFeature("http://xml.org/sax/features/namespaces", false);
        xmlReader.setFeature("http://xml.org/sax/features/validation", false);

        BaseHandler handler = new BaseHandler(new WordCounter());
        handler.setTagsToSkip(Arrays.asList("script", "noscript", "style", "meta", "html", "link", "img", "head"));
        xmlReader.setContentHandler(handler);
        xmlReader.parse(new InputSource(url.openStream()));
    }
}
