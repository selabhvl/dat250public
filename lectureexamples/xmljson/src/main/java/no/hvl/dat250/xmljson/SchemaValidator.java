package no.hvl.dat250.xmljson;

import org.xml.sax.SAXException;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class SchemaValidator {

    public static boolean validate(File schemaFile, File xmlFile) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(schemaFile);
            schema.newValidator().validate(new StreamSource(xmlFile));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        File schema = new File("src/main/resources/orders.xsd");
        File file = new File("src/main/resources/orders.xml");
        System.out.println(SchemaValidator.validate(schema,file));
    }

}
