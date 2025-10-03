package no.hvl.dat250.security.algorithms;


import org.springframework.security.crypto.bcrypt.BCrypt;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class Hashing {


    public static String doHash(String toHash) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("sha512");
        String salt = "salt";
        long before = System.currentTimeMillis();
        messageDigest.update(salt.getBytes(StandardCharsets.UTF_8));
        byte[] result = messageDigest.digest(toHash.getBytes(StandardCharsets.UTF_8));
        long after = System.currentTimeMillis();
        long diff = after - before;
        System.out.println("Hashed input with " + messageDigest.getAlgorithm() + " in " + diff + " ms. Resulting byte size:" + messageDigest.getDigestLength());
        return HexFormat.of().formatHex(result);
    }

    public static String doBcryptHash(String toHash) {
        long before = System.currentTimeMillis();
        String result = BCrypt.hashpw(toHash, "$2a$10$jUz13vECAtrExdArClGfv.");
        long after = System.currentTimeMillis();
        long diff = after - before;
        System.out.println("Hashed input with Bcrypt in " + diff + " ms");
        return result;
    }


    public static void main(String[] args) throws NoSuchAlgorithmException {
        String message = "12345";
        System.out.println("Input: " + message);
        String output = doBcryptHash(message);
        System.out.println("Output: " + output);
    }
}
