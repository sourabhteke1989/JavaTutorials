package com.maxlogic.springboottutorial.tokenization;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenizedDataDao extends CrudRepository<TokenizedDataEntity, Integer> {

    Optional<TokenizedDataEntity> findByAccountNumber(String accountNumber);

    Optional<TokenizedDataEntity> findByGlobalToken(String globalToken);
}
