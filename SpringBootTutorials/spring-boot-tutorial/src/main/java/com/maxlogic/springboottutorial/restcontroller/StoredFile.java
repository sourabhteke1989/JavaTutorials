package com.maxlogic.springboottutorial.restcontroller;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StoredFile {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  int id;

  private String name;

  private String contentType;

  @Lob private byte[] fileContent;
}
