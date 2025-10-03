package no.hvl.dat250.security.algorithms;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Encoding {

    public static String encode(byte[] data) {
        return Base64.getUrlEncoder().encodeToString(data);
    }

    public static byte[] decode(String text) {
        return Base64.getDecoder().decode(text);
    }

    public static void main(String[] args) {
        String input = "";
        String output = new String(decode(input));
        System.out.println(output);

    }
}
