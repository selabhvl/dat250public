package no.hvl.dat250.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PokeController {

    private final Logger logger = LoggerFactory.getLogger(PokeController.class);


    @GetMapping("/private/poke")
    public String poke() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        logger.info(authentication.getName() + " poked me !!!");
//        for (GrantedAuthority authority : authentication.getAuthorities()) {
//            logger.info("with role: " + authority.getAuthority());
//        }
//        logger.info("Authentication is of type: " + authentication.getClass().getCanonicalName());

        return "Stop poking me";
    }

}
