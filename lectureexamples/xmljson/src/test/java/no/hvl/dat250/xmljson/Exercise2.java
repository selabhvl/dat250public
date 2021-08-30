package no.hvl.dat250.xmljson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class Exercise2 {

    private static final String EXPECTED = "{\n" +
            "\t\"courses\": {\n" +
            "\t\t\"course\": {\n" +
            "\t\t\t\"semester\": \"H21\",\n" +
            "\t\t\t\"lecturers\": {\n" +
            "\t\t\t\t\"lecturer\": [\n" +
            "\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\"email\": \"lmkr@hvl.no\",\n" +
            "\t\t\t\t\t\t\"text\": \"Lars Mikael Kristensen\"\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\"email\": \"past@hvl.no\",\n" +
            "\t\t\t\t\t\t\"text\": \"Patrick Stünkel\"\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\"email\": \"tkra@hvl.no\",\n" +
            "\t\t\t\t\t\t\"text\": \"Tim Kräuter\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t]\n" +
            "\t\t\t},\n" +
            "\t\t\t\"ects\": \"10\",\n" +
            "\t\t\t\"level\": \"Master\",\n" +
            "\t\t\t\"schedule\": {\n" +
            "\t\t\t\t\"lecture\": [\n" +
            "\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\"day\": \"MON\",\n" +
            "\t\t\t\t\t\t\"start\": \"14:15\",\n" +
            "\t\t\t\t\t\t\"end\": \"16:00\",\n" +
            "\t\t\t\t\t\t\"location\": \"Zoom\"\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\"day\": \"FRI\",\n" +
            "\t\t\t\t\t\t\"start\": \"12:15\",\n" +
            "\t\t\t\t\t\t\"end\": \"14:00\",\n" +
            "\t\t\t\t\t\t\"location\": \"Zoom\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t],\n" +
            "\t\t\t\t\"exercise\": {\n" +
            "\t\t\t\t\t\"day\": \"WED\",\n" +
            "\t\t\t\t\t\"start\": \"10:15\",\n" +
            "\t\t\t\t\t\"end\": \"12:00\",\n" +
            "\t\t\t\t\t\"location\": \"M409\"\n" +
            "\t\t\t\t}\n" +
            "\t\t\t},\n" +
            "\t\t\t\"students\": {\n" +
            "\t\t\t\t\"student\": [\n" +
            "\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\"firstname\": \"Erna\",\n" +
            "\t\t\t\t\t\t\"lastname\": \"Solberg\",\n" +
            "\t\t\t\t\t\t\"id\": \"12345\"\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\"firstname\": \"Jonas\",\n" +
            "\t\t\t\t\t\t\"lastname\": \"Gahr Støre\",\n" +
            "\t\t\t\t\t\t\"id\": \"98765\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t]\n" +
            "\t\t\t},\n" +
            "\t\t\t\"code\": \"DAT250\"\n" +
            "\t\t}\n" +
            "\t}\n" +
            "}";

    @Test
    public void test() {
        File courses = new File("src/main/resources/courses.xml");
        File coursesTransformed = new File("src/main/resources/courses.json");
        transform(courses, coursesTransformed);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode expected = objectMapper.readValue(EXPECTED.getBytes(StandardCharsets.UTF_8), JsonNode.class);
            JsonNode actual = objectMapper.readValue(coursesTransformed, JsonNode.class);
            assertEquals(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(expected), objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(actual));
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }

    }


    private void transform(File xmlFile, File jsonFile) {
        // TODO implement this method!!
    }


}
