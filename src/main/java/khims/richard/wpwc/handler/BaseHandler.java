package khims.richard.wpwc.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;
import java.util.function.Consumer;

public class BaseHandler extends DefaultHandler {
    private Consumer<String> consumer;
    private List<String> tagsToSkip = new ArrayList<>();
    private StringBuilder buf = new StringBuilder();

    private String currentTag;
    private Queue<String> path = Collections.asLifoQueue(new ArrayDeque<>());

    public BaseHandler(Consumer<String> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void warning(SAXParseException e) throws SAXException {
        super.warning(e);
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        super.error(e);
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        super.fatalError(e);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (tagsToSkip.contains(qName)) return;
        path.add(qName);
        buf.setLength(0);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (Objects.isNull(path.peek())) return;
        if (!path.peek().equals(qName)) return;
        if (tagsToSkip.contains(qName)) return;

        System.out.println("Element: " + qName);
        path.remove();
        consumer.accept(buf.toString());
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (tagsToSkip.contains(currentTag)) return;
        buf.append(new String(ch));
    }

    public void setTagsToSkip(List<String> tagsToSkip) {
        this.tagsToSkip = tagsToSkip;
    }
}
