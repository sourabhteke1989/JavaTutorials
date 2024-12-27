package com.maxlogic.springboottutorial.tokenization;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DetokenizationInput {

    @JsonProperty("TOKEN_NUM")
    private String TOKEN_NUM;

}
