package no.hvl.dat250.springBoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private AppService service;


    @GetMapping("/hello")
    public String greeting() {
        return "Hei!";
    }

    @GetMapping("/quote")
    public Quote randomQuote() {
        return service.getRandomQuote();
    }

}
