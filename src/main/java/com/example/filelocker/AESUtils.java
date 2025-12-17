package com.example.filelocker;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.Arrays;

public class AESUtils {

    private static SecretKeySpec secretKey;

    // 1. Prepare the Key (Same as before)
    public static void setKey(String myKey) {
        try {
            byte[] key = myKey.getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 2. ENCRYPT FILE (New Feature)
    public static void encryptFile(File inputFile, File outputFile, String secret) throws Exception {
        setKey(secret);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Read all bytes from the file
        byte[] inputBytes = Files.readAllBytes(inputFile.toPath());
        // Encrypt them
        byte[] outputBytes = cipher.doFinal(inputBytes);
        // Write to the new file
        try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
            outputStream.write(outputBytes);
        }
    }

    // 3. DECRYPT FILE (New Feature)
    public static void decryptFile(File inputFile, File outputFile, String secret) throws Exception {
        setKey(secret);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] inputBytes = Files.readAllBytes(inputFile.toPath());
        byte[] outputBytes = cipher.doFinal(inputBytes);

        try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
            outputStream.write(outputBytes);
        }
    }
}
