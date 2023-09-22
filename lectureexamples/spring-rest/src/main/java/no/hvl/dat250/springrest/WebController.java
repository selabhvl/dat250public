package no.hvl.dat250.springrest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @GetMapping("/")
    public String index() {
        return "it works";
    }

    @GetMapping("/recommendations/bergen")
    public RecommendationDTO getRecommendationBergenn() {
        return RecommendationDTO.createRandom("bergen");
    }


}
