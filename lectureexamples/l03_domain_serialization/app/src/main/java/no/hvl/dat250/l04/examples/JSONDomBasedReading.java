package no.hvl.dat250.l04.examples;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class JSONDomBasedReading {

    public static void main(String[] args) {
        ObjectMapper om = new ObjectMapper();
        File inn = new File("result.json");
        try {
            JsonNode jsonNode = om.readTree(inn);

            System.out.println("Root element is an object:" + jsonNode.isObject());
            Iterator<String> fieldNames = jsonNode.fieldNames();
            while (fieldNames.hasNext()) {
                System.out.println("- " + fieldNames.next());
            }

            System.out.println("Body height :" + jsonNode.get("bodyHeightInCm"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
