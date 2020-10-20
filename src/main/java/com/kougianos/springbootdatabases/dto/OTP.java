package com.kougianos.springbootdatabases.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kougianos.springbootdatabases.enums.ServiceTypeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OTP {

    public OTP(String otp, String nonce) {
        this.otp = otp;
        this.nonce = nonce;
    }

    @JsonProperty("context")
    private String context = null;

    @JsonProperty("serviceId")
    private String serviceId = null;

    @JsonProperty("serviceType")
    private ServiceTypeEnum serviceType = null;

    @JsonProperty("otp")
    private String otp = null;

    @JsonProperty("nonce")
    private String nonce = null;

    @JsonProperty("channel")
    private Reference channel = null;

    @JsonProperty("validation")
    private String validation = null;

    @JsonProperty("characteristics")
    @Valid
    private List<Characteristic> characteristics = null;

}

