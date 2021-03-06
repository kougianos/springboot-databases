package com.kougianos.springbootdatabases.service;

import com.eatthepath.otp.TimeBasedOneTimePasswordGenerator;
import com.kougianos.springbootdatabases.dto.OTP;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;

@Service
public class OTPService {

    private TimeBasedOneTimePasswordGenerator totp;
    private Key key;

    public OTPService() throws NoSuchAlgorithmException {
        try {
            totp = new TimeBasedOneTimePasswordGenerator(Duration.ofSeconds(5));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public OTP generatePin() throws NoSuchAlgorithmException, InvalidKeyException {
        // Generate key
        KeyGenerator keyGenerator = KeyGenerator.getInstance(totp.getAlgorithm());
        keyGenerator.init(256);
        key = keyGenerator.generateKey();

        // Generate pin based on key and NOW timestamp
        int pin = totp.generateOneTimePassword(key, Instant.now());

        return new OTP(String.valueOf(pin), new String(key.getEncoded(), StandardCharsets.UTF_8)); 
    }

    public OTP validatePin(String pin, String nonce) throws InvalidKeyException {
        // decode the base64 encoded string
        byte[] decodedKey = Base64.getDecoder().decode(nonce);
        // rebuild key using SecretKeySpec
        key = new SecretKeySpec(decodedKey, 0, decodedKey.length, totp.getAlgorithm());

        // Generate new pin based on input key and NOW timestamp
        int newPin = totp.generateOneTimePassword(key, Instant.now());

        return new OTP(String.valueOf(newPin), Base64.getEncoder().encodeToString(key.getEncoded()));
    }

}

