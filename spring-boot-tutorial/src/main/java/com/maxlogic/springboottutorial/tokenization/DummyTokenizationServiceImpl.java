package com.maxlogic.springboottutorial.tokenization;

import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class DummyTokenizationServiceImpl implements DummyTokenizationService {

    private static final RandomStringGenerator randomStringGenerator;

    @Autowired
    TokenizedDataDao tokenizedDataDao;

    static{
        randomStringGenerator =
                new RandomStringGenerator.Builder()
                        .withinRange('0', 'z')
                        .filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS)
                        .build();
    }
    @Override
    public TokenizedData tokenize(String accountNumber) {
        TokenizedDataEntity tokenizedDataEntity;
        //Check if already exists in DB, return if already exists.
        Optional<TokenizedDataEntity> tokenizedDataEntityOpt = tokenizedDataDao.findByAccountNumber(accountNumber);
        if(tokenizedDataEntityOpt.isPresent()){
            return convertEntityToTokenizedData(tokenizedDataEntityOpt.get());
        }

        //Generate new globalToken which should not be present in DB already
        String globalToken;
        do {
            globalToken = randomStringGenerator.generate(128);
        } while(tokenizedDataDao.findByGlobalToken(globalToken).isPresent());

        //Create new entry in DB
        tokenizedDataEntity = TokenizedDataEntity.builder().accountNumber(accountNumber).globalToken(globalToken).build();
        tokenizedDataEntity = tokenizedDataDao.save(tokenizedDataEntity);
        return convertEntityToTokenizedData(tokenizedDataEntity);

    }

    @Override
    public DetokenizedData detokenize(String globalToken) {
        return convertEntityToDetokenizedData(tokenizedDataDao.findByGlobalToken(globalToken).get());
    }

    private TokenizedData convertEntityToTokenizedData(TokenizedDataEntity tokenizedDataEntity) {
        TokenizedData tokenizedData = new TokenizedData();
        tokenizedData.setGlobalToken(tokenizedDataEntity.getGlobalToken());
        tokenizedData.setMerchantToken("12345");
        return tokenizedData;
    }

    private DetokenizedData convertEntityToDetokenizedData(TokenizedDataEntity tokenizedDataEntity) {
        DetokenizedData detokenizedData = new DetokenizedData();
        detokenizedData.setCc(tokenizedDataEntity.getAccountNumber());
        detokenizedData.setExpMonth("12");
        detokenizedData.setExpYear("2030");
        return detokenizedData;
    }

}
