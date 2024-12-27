package com.maxlogic.springboottutorial.restcontroller;

import com.maxlogic.springboottutorial.service.IFileService;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

  @Autowired IFileService fileService;

  @PostMapping(path = "/upload")
  public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
    fileService.createFile(
        new StoredFile(0, file.getOriginalFilename(), file.getContentType(), file.getBytes()));
    return "success";
  }

  @GetMapping(path = "/get_files")
  public List<StoredFile> getAllFiles() {
    return fileService.getAllFiles();
  }

  @GetMapping(path = "/file/name")
  public ResponseEntity<Resource> downloadFile(@RequestParam("fileName") String fileName) {

    StoredFile storedFile = fileService.getFileByName(fileName);
    return ResponseEntity.ok()
        .header(
            HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + storedFile.getName() + "\"")
        .body(new ByteArrayResource(storedFile.getFileContent()));
  }
}
