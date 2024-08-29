package no.hvl.dat250.l04.examples;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JSONStreamBasedWriting {

    public static void main(String[] args) {
        File outout = new File("result.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getFactory();


        try {
            JsonGenerator generator = factory.createGenerator(outout, JsonEncoding.UTF8);
            generator.writeStartObject();
            generator.writeFieldName("name");
            generator.writeString("Patrick Stünkel");
            generator.writeFieldName("eyeColor");
            generator.writeString("brown");
            generator.writeFieldName("bodyHeightInCm");
            generator.writeNumber(128);
            generator.writeEndObject();
            generator.flush();
            generator.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
