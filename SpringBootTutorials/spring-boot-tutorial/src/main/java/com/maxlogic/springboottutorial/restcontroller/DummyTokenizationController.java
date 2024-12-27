package com.maxlogic.springboottutorial.restcontroller;

import com.maxlogic.springboottutorial.tokenization.*;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyTokenizationController {

    @Autowired
    DummyTokenizationService tokenizationService;

    @PostMapping("/dummy/tokenize/number")
    public ResponseEntity<TokenizationOutput> tokenize(@RequestBody TokenizationInput tokenizationInput) {
        if(tokenizationInput == null || StringUtils.isBlank(tokenizationInput.getPan())) {
            ResponseEntity.badRequest();
        }
        TokenizationOutput tokenizationOutput = new TokenizationOutput();
        tokenizationOutput.setData(tokenizationService.tokenize(tokenizationInput.getPan()));
        return ResponseEntity.ok(tokenizationOutput);
    }

    @PostMapping("/dummy/detokenize")
    public ResponseEntity<DetokenizationOutput> detokenize(@RequestBody DetokenizationInput detokenizationInput) {
        if(detokenizationInput == null || StringUtils.isBlank(detokenizationInput.getTOKEN_NUM())) {
            ResponseEntity.badRequest();
        }
        DetokenizationOutput detokenizationOutput = new DetokenizationOutput();
        try {
            detokenizationOutput.setData(tokenizationService.detokenize(detokenizationInput.getTOKEN_NUM()));
        } catch(Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(detokenizationOutput);
    }



}
