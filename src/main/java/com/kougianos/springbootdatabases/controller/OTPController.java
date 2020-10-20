package com.kougianos.springbootdatabases.controller;

import com.kougianos.springbootdatabases.dto.OTP;
import com.kougianos.springbootdatabases.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@CrossOrigin
@RestController
@RequestMapping(path = "otp")
public class OTPController {

    @Autowired
    private OTPService otpService;

    @PostMapping(path = "/generate")
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody
    OTP generatePin(
            @RequestBody OTP otp
    ) throws InvalidKeyException, NoSuchAlgorithmException {
        OTP generatedOtp = otpService.generatePin();
        otp.setNonce(generatedOtp.getNonce());
        otp.setOtp(generatedOtp.getOtp());
        return otp;
    }

    @PutMapping(path = "/validate")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    OTP validatePin(
            @RequestBody OTP otp
    ) throws InvalidKeyException {
        OTP generatedOtp = otpService.validatePin(otp.getOtp(), otp.getNonce());
        otp.setNonce(generatedOtp.getNonce());
        otp.setOtp(generatedOtp.getOtp());
        otp.setValidation(generatedOtp.getValidation());
        return otp;
    }

}
