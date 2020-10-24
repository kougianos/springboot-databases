package com.kougianos.springbootdatabases.service;

import com.kougianos.springbootdatabases.dto.OTP;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface OTPService {

    /**
     * Generate a new OTP
     *
     * @return OTP object created
     */
    OTP generatePin() throws NoSuchAlgorithmException, InvalidKeyException;

    /**
     * Validate an existing OTP
     *
     * @param pin One time pin to validate
     * @param nonce Key created at generatePin
     * @return OTP object validated, that includes result in validation field
     */
    OTP validatePin(String pin, String nonce) throws InvalidKeyException;
}
