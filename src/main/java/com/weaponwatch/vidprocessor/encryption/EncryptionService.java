package com.weaponwatch.vidprocessor.encryption;

/*
    TODO: Excpetion Handling
 */

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;

public class EncryptionService {
    private static final String AES_TRANSFORMATION = "AES/GCM/NoPadding";
    private static final int GCM_TAG_LENGTH = 128;
    private static final int IV_SIZE = 12;

    public void encryptFile(byte[] key, String fileInPath, String fileOutPath) throws Exception {
        Path inAsPath = Paths.get(fileInPath);
        byte[] fileData = Files.readAllBytes(inAsPath);

        // random IV generation
        byte[] iv = new byte[IV_SIZE];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(iv);

        // cipher initialization
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
        Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, gcmParameterSpec);

        byte[] encryptedData = cipher.doFinal(fileData);
        try (FileOutputStream fos = new FileOutputStream(fileOutPath)) {
            fos.write(iv);
            fos.write(encryptedData);
        }

    }
}
