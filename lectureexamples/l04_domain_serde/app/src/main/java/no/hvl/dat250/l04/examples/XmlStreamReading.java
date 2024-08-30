package no.hvl.dat250.l04.examples;

import com.google.common.collect.Maps;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Map;

public class XmlStreamReading extends DefaultHandler {

    private final Map<String, Double> resultMap = Maps.newHashMap();
    private String currentOrder;
    private double currentTotal;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("order".equals(qName)) {
            if (currentOrder != null) {
                resultMap.put(currentOrder, currentTotal);
            }
            currentOrder = attributes.getValue("orderNo");
            currentTotal = 0.0;
        }
        if ("item".equals(qName)) {
            int quantity = Integer.parseInt(attributes.getValue("quantity"));
            double price = Double.parseDouble(attributes.getValue("unitPrice"));
            currentTotal += price * quantity;
        }
    }

    @Override
    public void endDocument() throws SAXException {
        if (currentOrder != null) {
            resultMap.put(currentOrder, currentTotal);
        }
    }

    public static void main(String[] args) {
        try {
            XmlStreamReading reader = new XmlStreamReading();
            SAXParser parser = SAXParserFactory.newDefaultInstance().newSAXParser();
            parser.parse(XmlStreamReading.class.getResourceAsStream("/data/orders.xml"), reader);

            System.out.println("Total price per order:");
            for (Map.Entry<String, Double> e : reader.resultMap.entrySet()) {
                System.out.printf("Order %s: %.2f\n", e.getKey(), e.getValue());
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }




}
