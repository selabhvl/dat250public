package no.hvl.dat250.xmljson;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonDatabind {

    public static void writeCatalog(Catalog catalog, File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(file, catalog);
    }

    public static Catalog readCatalog(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(file, Catalog.class);
    }

}
