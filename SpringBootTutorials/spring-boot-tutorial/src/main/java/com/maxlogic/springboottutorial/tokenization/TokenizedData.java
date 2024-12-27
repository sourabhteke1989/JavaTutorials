package com.maxlogic.springboottutorial.tokenization;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

import java.io.Serializable;

@Data
public class TokenizedData implements Serializable {

    private static final long serialVersionUID = 1577476865090512414L;

    private String globalToken;
    private String merchantToken;

    @JsonSetter("GlobalToken")
    public void setGlobalToken(String globalToken) {
        this.globalToken = globalToken;
    }


    @JsonSetter("MerchantToken")
    public void setMerchantToken(String merchantToken) {
        this.merchantToken = merchantToken;
    }

}
