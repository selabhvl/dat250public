package no.hvl.dat250.l03;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import no.hvl.dat250.l04.examples.domains.Filesystem;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * In this challenge, you will have to deal with a hierarchical data structure,
 * namely a file system comprising files, folders, and symbolic links.
 * The associations in this class structure contain different various
 * _cycles_, e.g. a bidirectional parent-child relationship between folders and its child elements, or
 * symlinks that can point to arbitrary folders or files and thus create new cycles.
 */
public class Challenge2 {

    private static final String EXPECTED_HIERARCHICAL = """
            {
              "name" : "C:",
              "contents" : [ {
                "name" : "System",
                "contents" : [ {
                  "name" : "win32.dll",
                  "sizeInBytes" : 5182222
                } ]
              }, {
                "name" : "Users",
                "contents" : [ {
                  "name" : "poem.txt",
                  "sizeInBytes" : 123
                }, {
                  "name" : "image.jpg",
                  "sizeInBytes" : 4223
                } ]
              } ]
            }
            """;

    @Test
    public void hierarchical() throws IOException {
        Filesystem.Folder cDrive = new Filesystem.Folder();
        cDrive.setName("C:");
        Filesystem.Folder users = new Filesystem.Folder();
        users.setName("Users");
        Filesystem.Folder system = new Filesystem.Folder();
        system.setName("System");
        Filesystem.File win32 = new Filesystem.File("win32.dll", system, 5_182_222);
        system.getContents().add(win32);
        Filesystem.File file = new Filesystem.File("poem.txt", users, 123);
        users.getContents().add(file);
        Filesystem.File file2 = new Filesystem.File("image.jpg", users, 4_223);
        users.getContents().add(file2);

        users.setParent(cDrive);
        system.setParent(cDrive);
        cDrive.getContents().add(system);
        cDrive.getContents().add(users);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        // TODO: currently this crashes because of the parent-child relationship creating a cycle, can you fix it?
        mapper.writeValue(bos, cDrive);


        assertEquals(EXPECTED_HIERARCHICAL.trim(), bos.toString(StandardCharsets.UTF_8).trim());
    }

    @Test
    public void anotherCycle() {

    }

}
