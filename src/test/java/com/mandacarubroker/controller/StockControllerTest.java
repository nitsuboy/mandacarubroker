package com.mandacarubroker.controller;

import com.mandacarubroker.request.RequestStockDTO;
import com.mandacarubroker.service.StockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
class StockControllerTest {

  @MockBean
  private StockService stockService;

  @Autowired
  private StockController stockController;

  private RequestStockDTO newStock1;

  @BeforeEach
  void setup(){
    MockitoAnnotations.initMocks(this);
    this.newStock1 = new RequestStockDTO("aaa2","aero",41);
  }

  @Test
  void getAllStocks() {
    stockController.getAllStocks();

    Mockito.verify(stockService,Mockito.times(1)).getAllStocks();
  }

  @Test
  void getStockById() {
    stockController.getStockById("0");

    Mockito.verify(stockService,Mockito.times(1)).getStockById(Mockito.any());
  }

  @Test
  void createStock() {
    stockController.createStock(newStock1);

    Mockito.verify(stockService,Mockito.times(1)).createStock(Mockito.any());
  }

  @Test
  void updateStock() {
    stockController.updateStock("0",newStock1);

    Mockito.verify(stockService,Mockito.times(1)).updateStock(Mockito.any(),Mockito.any());
  }

  @Test
  void deleteStock() {
    stockController.deleteStock("0");

    Mockito.verify(stockService,Mockito.times(1)).deleteStock(Mockito.any());
  }
}