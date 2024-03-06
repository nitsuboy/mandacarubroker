package com.mandacarubroker.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

  private User newUser1;
  @BeforeEach
  void setup(){
    this.newUser1 = new User("0",
        "aaa",
        "aaa",
        "aaa",
        "aaa",
        "aaa",
        LocalDate.of(2000,2,1),
        0);
  }
  @Test
  void getId() { Assertions.assertEquals("0",newUser1.getId());}

  @Test
  void getUsername() { Assertions.assertEquals("aaa",newUser1.getUsername());}

  @Test
  void getPassword() { Assertions.assertEquals("aaa",newUser1.getPassword());}

  @Test
  void getEmail() { Assertions.assertEquals("aaa",newUser1.getEmail());}

  @Test
  void getFirstName() { Assertions.assertEquals("aaa",newUser1.getFirstName());}

  @Test
  void getLastName() { Assertions.assertEquals("aaa",newUser1.getLastName());}

  @Test
  void getBirthDate() { Assertions.assertEquals(LocalDate.of(2000,2,1),newUser1.getBirthDate());}

  @Test
  void getBalance() { Assertions.assertEquals(0,newUser1.getBalance());}

  @Test
  void setId() {
    newUser1.setId("1");
    Assertions.assertEquals("1",newUser1.getId());
  }

  @Test
  void setUsername() {
    newUser1.setUsername("bab");
    Assertions.assertEquals("bab",newUser1.getUsername());
  }

  @Test
  void setPassword() {
    newUser1.setPassword("bab");
    Assertions.assertEquals("bab",newUser1.getPassword());
  }

  @Test
  void setEmail() {
    newUser1.setEmail("bab");
    Assertions.assertEquals("bab",newUser1.getEmail());
  }

  @Test
  void setFirstName() {
    newUser1.setFirstName("bab");
    Assertions.assertEquals("bab",newUser1.getFirstName());
  }

  @Test
  void setLastName() {
    newUser1.setLastName("bab");
    Assertions.assertEquals("bab",newUser1.getLastName());
  }

  @Test
  void setBirthDate() {
    newUser1.setBirthDate(LocalDate.of(2001,1,2));
    Assertions.assertEquals(LocalDate.of(2001,1,2),newUser1.getBirthDate());
  }

  @Test
  void setBalance() {
    newUser1.setBalance(2);
    Assertions.assertEquals(2,newUser1.getBalance());
  }
}