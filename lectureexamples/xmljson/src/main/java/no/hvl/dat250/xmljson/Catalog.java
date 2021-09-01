package no.hvl.dat250.xmljson;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "catalog")
public class Catalog {

    private List<Book> books;

    public Catalog() {
    }

    public Catalog(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    @XmlElement(name = "book")
    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
