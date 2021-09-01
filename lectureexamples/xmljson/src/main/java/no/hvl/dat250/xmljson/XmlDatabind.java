package no.hvl.dat250.xmljson;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;

public class XmlDatabind {

    public static void writeCatalog(Catalog catalog, File booksFile) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Book.class, Catalog.class);
        jaxbContext.createMarshaller().marshal(catalog,booksFile);
    }

    public static Catalog readCatalog(File booksFile) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Book.class, Catalog.class);
        return (Catalog) jaxbContext.createUnmarshaller().unmarshal(booksFile);
    }


}
