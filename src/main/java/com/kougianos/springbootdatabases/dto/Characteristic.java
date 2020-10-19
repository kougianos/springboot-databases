package com.kougianos.springbootdatabases.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Characteristic {
    @JsonProperty("name")
    private String name = null;

    @JsonProperty("value")
    private String value = null;

    public Characteristic name(String name) {
        this.name = name;
        return this;
    }

}

