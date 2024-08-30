package no.hvl.dat250.l04.examples;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XmlDomBasedWriting {

    public static void main(String[] args) throws ParserConfigurationException {

        // Building a document object structure programmatically
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element root = doc.createElement("person");
        root.setAttribute("firstname", "Harald");
        root.setAttribute("lastname", "König");
        Element address = doc.createElement("address");
        Element street = doc.createElement("street");
        Element number = doc.createElement("number");
        street.appendChild(doc.createTextNode("Main Street"));
        number.appendChild(doc.createTextNode("28"));
        address.appendChild(street);
        address.appendChild(number);
        root.appendChild(address);
        doc.appendChild(root);

        // Writing it out...
        try {

            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            // enable pretty printing
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");


            DOMSource source = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(new File("person.xml"));
            transformer.transform(source, streamResult);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}
