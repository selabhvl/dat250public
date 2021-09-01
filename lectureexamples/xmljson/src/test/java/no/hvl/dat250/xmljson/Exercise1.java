package no.hvl.dat250.xmljson;


import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class Exercise1 {

    @Test
    public void test() {
        // TODO make this test green
        assertTrue(SchemaValidator.validate(new File("src/main/resources/courses.xsd"), new File("src/main/resources/courses.xml")));
    }
}
