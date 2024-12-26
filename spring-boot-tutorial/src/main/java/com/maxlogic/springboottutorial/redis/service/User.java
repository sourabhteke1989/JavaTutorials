package com.maxlogic.springboottutorial.redis.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {
    private Integer userId;
    private String userName;
    private Boolean isSystemToken;
}
