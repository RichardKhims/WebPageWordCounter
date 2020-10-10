package khims.richard.wpwc.parser;

import khims.richard.wpwc.handler.sax.BaseHandler;
import org.ccil.cowan.tagsoup.Parser;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.InputStream;

public class SaxParser implements SourceParser {
    private BaseHandler handler;

    public SaxParser(BaseHandler handler) {
        this.handler = handler;
    }

    @Override
    public void parse(InputStream input) throws Exception {
        XMLReader xmlReader = new Parser();
        xmlReader.setFeature("http://xml.org/sax/features/namespaces", false);
        xmlReader.setFeature("http://xml.org/sax/features/validation", false);
        xmlReader.setContentHandler(handler);
        xmlReader.parse(new InputSource(input));
    }
}
