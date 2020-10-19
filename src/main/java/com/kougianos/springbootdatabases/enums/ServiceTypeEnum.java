package com.kougianos.springbootdatabases.enums;

public enum ServiceTypeEnum {
    PHONENUMBER("phoneNumber"),
    EMAIL("email"),
    TVACCOUNT("tvAccount"),
    INTERNETACCOUNT("internetAccount"),
    MOBILEINTERNET("mobileInternet"),
    IMSI("IMSI"),
    MTID("mtID"),
    PROFILE("profile");

    ServiceTypeEnum(String value) {
    }

}
