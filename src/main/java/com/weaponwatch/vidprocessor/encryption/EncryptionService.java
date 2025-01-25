package com.weaponwatch.vidprocessor.encryption;

/*
    TODO: Excpetion Handling
 */
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;

public class EncryptionService {
    private static final String AES_TRANSFORMATION = "AES/GCM/NoPadding";
    private static final int GCM_TAG_LENGTH = 128;
    private static final int IV_SIZE = 12;

    public void encryptFile(byte[] key, Path inputPath, Path outputPath) throws Exception{
        byte[] fileData = Files.readAllBytes(inputPath);

        Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION); // AES GCM instance
        byte[] iv = new byte[IV_SIZE]; // random IV generation

        SecureRandom random = new SecureRandom(); // for RNG
        random.nextBytes(iv);

        // cipher initialization
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, gcmParameterSpec);

        byte[] cipherText = cipher.doFinal(fileData);
        byte[] outputBytes = new byte[iv.length + cipherText.length];

        System.arraycopy(iv, 0, outputBytes, 0, iv.length);
        System.arraycopy(cipherText, 0, outputBytes, iv.length, cipherText.length);

        Files.write(outputPath, outputBytes);
    }
}
