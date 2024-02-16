package com.mandacarubroker.service;

import com.mandacarubroker.model.Stock;
import com.mandacarubroker.repository.StockRepository;
import com.mandacarubroker.request.RequestStockDTO;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.sql.ast.tree.expression.Over;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StockServiceTest {

  @Mock
  private StockRepository repository;

  @Autowired
  @InjectMocks
  private StockService stockService;

  private RequestStockDTO newStock1;

  @BeforeEach
  void setup(){
    MockitoAnnotations.initMocks(this);
    this.newStock1 = new RequestStockDTO("aaa2","aero",41);
  }

  @Test
  @DisplayName("Deve Retornar todos os stocks")
  void getAllStocks() {
    stockService.getAllStocks();

    Mockito.verify(repository, Mockito.times(1)).findAll();
  }

  @Test
  @DisplayName("Deve Retornar Stock pelo id")
  void getStockById() {
    stockService.getStockById("0");

    Mockito.verify(repository, Mockito.times(1)).findById(Mockito.any());
  }

  @Test
  @DisplayName("Deve criar um stock novo")
  void createStockCase1() {
    stockService.createStock(newStock1);

    Mockito.verify(repository, Mockito.times(1)).save(Mockito.any());
  }

  @Test
  @DisplayName("Não deve criar um stock novo, simbolo")
  void createStockCase2() {
    RequestStockDTO newStock1 = new RequestStockDTO("aaaa1","aeros",40);

    Exception exception = assertThrows(ConstraintViolationException.class, () -> {stockService.createStock(newStock1);});

    Mockito.verify(repository, Mockito.times(0)).save(Mockito.any());
    Assertions.assertEquals("Validation failed. Details: [symbol: Symbol must be 3 letters followed by 1 number]", exception.getMessage());
  }

  @Test
  @DisplayName("Não deve criar um stock novo, nome")
  void createStockCase3() {
    RequestStockDTO newStock1 = new RequestStockDTO("aaa1",null,40);

    Exception exception = assertThrows(ConstraintViolationException.class, () -> {stockService.createStock(newStock1);});

    Mockito.verify(repository, Mockito.times(0)).save(Mockito.any());
    Assertions.assertEquals("Validation failed. Details: [companyName: Company name cannot be blank]", exception.getMessage());
  }

  @Test
  @DisplayName("Deve fazer update no Stock")
  void updateStock() {
    Stock stock = new Stock("0","aaa1","aeros",41);

    Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(stock));
    stockService.updateStock("0",newStock1);

    Mockito.verify(repository, Mockito.times(1)).save(Mockito.any());
    Mockito.verify(repository, Mockito.times(1)).findById(Mockito.any());
  }

  @Test
  @DisplayName("Deve excluir update no Stock")
  void deleteStock() {
    stockService.deleteStock("0");

    Mockito.verify(repository, Mockito.times(1)).deleteById(Mockito.any());
  }
}