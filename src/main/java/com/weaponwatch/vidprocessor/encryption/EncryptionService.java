package com.weaponwatch.vidprocessor.encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.SecureRandom;

public class EncryptionService {
    private final String AES_TRANSFORMATION = "AES/GCM/NoPadding";

}
