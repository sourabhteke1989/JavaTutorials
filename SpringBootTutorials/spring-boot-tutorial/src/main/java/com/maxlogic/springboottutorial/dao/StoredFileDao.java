package com.maxlogic.springboottutorial.dao;

import com.maxlogic.springboottutorial.restcontroller.StoredFile;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoredFileDao extends CrudRepository<StoredFile, Integer> {
    Optional<StoredFile> findByName(String name);
}
