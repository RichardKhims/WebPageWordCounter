package khims.richard.wpwc.parser;

import khims.richard.wpwc.handler.SaxHandler;
import org.ccil.cowan.tagsoup.Parser;
import org.xml.sax.InputSource;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;

import java.io.InputStream;

public class SaxParser implements SourceParser {
    private XMLReader xmlReader;

    public SaxParser(SaxHandler handler) throws SAXNotRecognizedException, SAXNotSupportedException {
        xmlReader = new Parser();
        xmlReader.setFeature("http://xml.org/sax/features/namespaces", false);
        xmlReader.setFeature("http://xml.org/sax/features/validation", false);
        xmlReader.setContentHandler(handler);
    }

    @Override
    public void parse(InputStream input) throws Exception {
        xmlReader.parse(new InputSource(input));
    }
}
