package no.hvl.dat250.l04.examples;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.common.collect.Sets;
import no.hvl.dat250.l04.examples.domains.Polls;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class JsonDataBinding {


    private static ObjectMapper initJackson() {
        ObjectMapper mapper = new ObjectMapper();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        return mapper;
    }

    public static void main(String[] args) {

        Polls.User user = new Polls.User("user", "john.doe@example.com");
        Polls.VoteOption yes = new Polls.VoteOption(0, "Of course!");
        Polls.VoteOption no = new Polls.VoteOption(1, "Mamma mia! Noooooo");
        Polls.Poll poll = new Polls.Poll("Annanas on Pizza: Is it ok?", Sets.newHashSet(yes, no), user);
        poll.setClosesAt(LocalDateTime.of(2024, 9, 3, 21, 59, 59).toInstant(ZoneOffset.UTC));

        ObjectMapper mapper = initJackson();

        // Things to look at:
        // - JsonIgnore
        // - JsonIdentityReference, JsonIdentityInfo, ObjectIdGenerator
        // - JsonSerialize, JsonDeserialize together with JsonSerializer, JsonDeserializer


        try {
            mapper.writeValue(new File("test.json"), poll);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}
