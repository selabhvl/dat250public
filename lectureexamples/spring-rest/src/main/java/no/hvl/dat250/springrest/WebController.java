package no.hvl.dat250.springrest;

import org.springframework.web.bind.annotation.*;

@RestController
public class WebController {

    @GetMapping("/")
    public String index() {
        return "it works";
    }


    @GetMapping("/recommendations/{location}")
    public RecommendationDTO getRecommendationBergenn(
            @PathVariable String location
    ) {
        return RecommendationDTO.createRandom(location);
    }


}
