package no.hvl.dat250.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PokeController {


    @GetMapping("/private/poke")
    public String poke() {
        return "Stop poking me";
    }

}
