package com.maxlogic.springboottutorial.service;

import com.maxlogic.springboottutorial.restcontroller.StoredFile;
import java.util.List;

public interface IFileService {

  void createFile(StoredFile file);

  List<StoredFile> getAllFiles();

  StoredFile getFile(int id);

  StoredFile getFileByName(String name);
}
