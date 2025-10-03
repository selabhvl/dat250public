package no.hvl.dat250.security.algorithms;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class Encryption {


    public static byte[] encryptWithAES(SecretKey key, String message, GCMParameterSpec gcmIv) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, key, gcmIv);
        return cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
    }

    public static String decryptAES(SecretKey key, byte[] cyphertext, GCMParameterSpec gcmIv) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, key, gcmIv);
        byte[] result = cipher.doFinal(cyphertext);
        return new String(result, StandardCharsets.UTF_8);
    }

    public static SecretKey makeAESKey(String pass) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        PBEKeySpec spec = new PBEKeySpec(pass.toCharArray(), "salted".getBytes(), 10_000, 256);
        SecretKey secretKey = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
        return secretKey;
    }

    public static SecretKey makeRandomAESKey() throws NoSuchAlgorithmException {
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(256);
        return generator.generateKey();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, new byte[]{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b});
        SecretKey secretKey = makeAESKey("12345");
        String message = "Have a nice weekend!";
        byte[] ciphertext = encryptWithAES(secretKey, message, gcmParameterSpec);
        String cipherEncoded = Base64.getEncoder().encodeToString(ciphertext);
        System.out.println("Ciphertext (base64): " + cipherEncoded);
        String decoded = decryptAES(secretKey, ciphertext, gcmParameterSpec);
        System.out.println("Decoded: " + decoded);

    }


}
