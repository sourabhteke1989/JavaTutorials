package com.maxlogic.springboottutorial.restcontroller;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@XmlRootElement
public class UserCredentials {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String userName;

  private String password;
}
