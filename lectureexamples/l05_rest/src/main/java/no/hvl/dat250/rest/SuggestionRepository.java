package no.hvl.dat250.rest;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SuggestionRepository {

    private final Map<String, Suggestion> map = HashMap.newHashMap(2);

    public SuggestionRepository() {
        map.put("bergen", new Suggestion(Suggestion.Gadget.SUNGLASSES, 12));
        map.put("oslo", new Suggestion(Suggestion.Gadget.UMBRELLA, 38));
    }

    public Map<String, Suggestion> getMap() {
        return map;
    }
}
