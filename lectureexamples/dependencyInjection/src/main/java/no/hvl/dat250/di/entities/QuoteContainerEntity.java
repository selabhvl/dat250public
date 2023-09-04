package no.hvl.dat250.di.entities;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "quotes")
public class QuoteContainerEntity {

    private List<QuoteEntity> quotes;

    public QuoteContainerEntity(List<QuoteEntity> quotes) {
        this.quotes = quotes;
    }

    public QuoteContainerEntity() {
    }

    public List<QuoteEntity> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<QuoteEntity> quotes) {
        this.quotes = quotes;
    }
}
