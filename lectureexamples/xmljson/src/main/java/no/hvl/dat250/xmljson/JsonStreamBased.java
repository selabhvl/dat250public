package no.hvl.dat250.xmljson;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;

import java.io.File;
import java.io.IOException;

public class JsonStreamBased {

    public static int countTotalQuantity(File ordersFile) throws IOException {
        JsonFactory jsonFactory = new JsonFactory();
        JsonParser parser = jsonFactory.createParser(ordersFile);

        int sum = 0;
        while (parser.nextToken() != null) {
            String currentName = parser.getCurrentName();
            if (currentName != null) {
                if ("quantity".equals(currentName)) {
                    parser.nextToken();
                    sum += parser.getIntValue();
                }
            }
        }
        return sum;
    }


    public static void totalQuantity(int quantity, File file) throws IOException {
        JsonFactory jsonFactory = new JsonFactory();
        JsonGenerator generator = jsonFactory.createGenerator(file, JsonEncoding.UTF8);
        generator.writeStartObject();
        generator.writeFieldName("totalQuantity");
        generator.writeNumber(quantity);
        generator.writeEndObject();
        generator.flush();
        generator.close();
    }

    public static void main(String[] args) {
        File ordersFile = new File("src/main/resources/orders.json");
        File resultFile = new File("src/main/resources/totalQuantity.json");
        try {
            int i = JsonStreamBased.countTotalQuantity(ordersFile);
            JsonStreamBased.totalQuantity(i, resultFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
