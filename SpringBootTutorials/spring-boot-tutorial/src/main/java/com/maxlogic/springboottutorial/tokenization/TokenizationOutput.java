package com.maxlogic.springboottutorial.tokenization;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class TokenizationOutput {
    private TokenizedData data;
    private String error;
    private List<String> messages;
    private String time;
}
