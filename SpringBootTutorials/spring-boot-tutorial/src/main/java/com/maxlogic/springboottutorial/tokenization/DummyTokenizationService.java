package com.maxlogic.springboottutorial.tokenization;

public interface DummyTokenizationService {

    TokenizedData tokenize(String accountNumber);

    DetokenizedData detokenize(String globalToken);
}
