package com.maxlogic.springboottutorial.tokenization;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TokenizationInput {

    @JsonProperty("MID")
    private String mid;

    @JsonProperty("PAN")
    private String pan;

}
