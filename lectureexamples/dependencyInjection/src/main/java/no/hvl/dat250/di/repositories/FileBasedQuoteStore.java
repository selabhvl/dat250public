package no.hvl.dat250.di.repositories;

import no.hvl.dat250.di.domain.Quote;
import no.hvl.dat250.di.entities.QuoteContainerEntity;
import no.hvl.dat250.di.entities.QuoteEntity;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class FileBasedQuoteStore implements QuoteStorage {

    private QuoteContainerEntity quoteContainer;

    private JAXBContext context;

    private String fileLocation;

    public FileBasedQuoteStore(String fileLocation) {
        this.fileLocation = fileLocation;
        File location = new File(fileLocation);
        if (location.exists()) {
            try {
                context = JAXBContext.newInstance(QuoteContainerEntity.class, QuoteEntity.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                quoteContainer = (QuoteContainerEntity) unmarshaller.unmarshal(new File(fileLocation));
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                context = JAXBContext.newInstance(QuoteContainerEntity.class, QuoteEntity.class);
                quoteContainer = new QuoteContainerEntity();
                quoteContainer.setQuotes(new ArrayList<>());
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public Collection<Quote> loadQuotes() {
        return quoteContainer.getQuotes()
                .stream()
                .map(q -> {
                    Quote result = new Quote(q.getText(), q.getWho());
                    for (int i = 0; i < q.getVotes(); i++) {
                        result.upvote();
                    }
                    return result;
                })
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public void storeQuote(Quote q) {
        quoteContainer.getQuotes().add(new QuoteEntity(q.getText(), q.getWhoSaidIt(), q.getVotes()));
        try {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(quoteContainer, new File(fileLocation));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateQuote(Quote q) {
        for (QuoteEntity qe : quoteContainer.getQuotes()) {
            if (qe.getText().equals(q.getText())) {
                qe.setWho(q.getWhoSaidIt());
                qe.setVotes(q.getVotes());
            }
        }
        try {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(quoteContainer, new File(fileLocation));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
