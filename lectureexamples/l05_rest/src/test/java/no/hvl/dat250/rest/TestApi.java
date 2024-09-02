package no.hvl.dat250.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestApi {




    @Test
    public void testGetPredefinedLocations(@Autowired TestRestTemplate restTemplate) throws Exception {
        ResponseEntity<Suggestion> entity = restTemplate.getForEntity(URI.create("/suggest/bergen"), Suggestion.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals(new Suggestion(Suggestion.Gadget.SUNGLASSES, 12), entity.getBody());

        entity = restTemplate.getForEntity(URI.create("/suggest/oslo"), Suggestion.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals(new Suggestion(Suggestion.Gadget.UMBRELLA, 38), entity.getBody());

        entity = restTemplate.getForEntity(URI.create("/suggest/stavanger"), Suggestion.class);
        assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());

    }

    @Test
    public void testPostLocation(@Autowired TestRestTemplate restTemplate) throws Exception {
        ResponseEntity<Suggestion> entity = restTemplate.postForEntity(URI.create("/suggest/stavanger"), new Suggestion(Suggestion.Gadget.NOTHING, 4) ,Suggestion.class);
        assertEquals(HttpStatus.CREATED, entity.getStatusCode());
        assertEquals(new Suggestion(Suggestion.Gadget.NOTHING, 4), entity.getBody());

        entity = restTemplate.getForEntity(URI.create("/suggest/stavanger"), Suggestion.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals(new Suggestion(Suggestion.Gadget.NOTHING, 4), entity.getBody());


    }

}
