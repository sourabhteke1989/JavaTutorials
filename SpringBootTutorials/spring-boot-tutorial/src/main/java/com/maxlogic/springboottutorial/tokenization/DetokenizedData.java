package com.maxlogic.springboottutorial.tokenization;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

@Data
public class DetokenizedData {

    private String cc;
    private String expYear;
    private String expMonth;

    @JsonSetter("cc")
    public void setCc(String cc) {
        this.cc = cc;
    }


    @JsonSetter("EXP_YEAR")
    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }

    @JsonSetter("EXP_MONTH")
    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }

}
