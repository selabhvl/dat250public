package no.hvl.dat250.springBoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AppService {

    @Autowired
    private QuoteRepository repo;

    public Quote getRandomQuote() {
        List<QuoteEntity> list = StreamSupport.stream(repo.findAll().spliterator(), false).toList();
        int idx = new Random().nextInt(list.size());
        QuoteEntity entity = list.get(idx);
        return new Quote(entity.getQuote(), entity.getAuthor());
    }
}
