package com.mandacarubroker.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StockTest {

  private Stock newStock1;

  @BeforeEach
  void setup(){
    this.newStock1 = new Stock("0","aaa1","aero",40);
  }

  @Test
  void getId() {
    Assertions.assertEquals("0",newStock1.getId());
  }

  @Test
  void getSymbol() {
    Assertions.assertEquals("aaa1",newStock1.getSymbol());
  }

  @Test
  void getCompanyName() {
    Assertions.assertEquals("aero",newStock1.getCompanyName());
  }

  @Test
  void getPrice() {
    Assertions.assertEquals(40,newStock1.getPrice());
  }

  @Test
  void setId() {
    newStock1.setId("1");
    Assertions.assertEquals("1",newStock1.getId());
  }

  @Test
  void setSymbol() {
    newStock1.setSymbol("aaa3");
    Assertions.assertEquals("aaa3",newStock1.getSymbol());
  }

  @Test
  void setCompanyName() {
    newStock1.setCompanyName("uga");
    Assertions.assertEquals("uga",newStock1.getCompanyName());
  }

  @Test
  void setPrice() {
    newStock1.setPrice(41);
    Assertions.assertEquals(41,newStock1.getPrice());
  }
}