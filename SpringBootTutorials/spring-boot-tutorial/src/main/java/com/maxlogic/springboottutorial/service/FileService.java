package com.maxlogic.springboottutorial.service;

import com.maxlogic.springboottutorial.dao.StoredFileDao;
import com.maxlogic.springboottutorial.restcontroller.StoredFile;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService implements IFileService {

  @Autowired StoredFileDao storedFileDao;

  @Override
  public void createFile(StoredFile file) {

    storedFileDao.save(file);
  }

  @Override
  public List<StoredFile> getAllFiles() {
    List<StoredFile> fileList = new ArrayList<>();
    for (StoredFile storedFile : storedFileDao.findAll()) {
      fileList.add(
          new StoredFile(
              storedFile.getId(), storedFile.getName(), storedFile.getContentType(), null));
    }
    return fileList;
  }

  @Override
  public StoredFile getFile(int id) {
    return storedFileDao.findById(id).get();
  }

  @Override
  public StoredFile getFileByName(String fileName) {
    return storedFileDao.findByName(fileName).get();
  }
}
