package com.mandacarubroker.controller;

import com.mandacarubroker.model.Stock;
import com.mandacarubroker.request.RequestStockDTO;
import com.mandacarubroker.service.StockService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller Stocks.
 */
@RestController
@RequestMapping("/stocks")
public class StockController {
  private final StockService stockService;

  public StockController(StockService stockService) {
    this.stockService = stockService;
  }

  @GetMapping
  public List<Stock> getAllStocks() {
    return stockService.getAllStocks();
  }

  @GetMapping("/{id}")
  public Stock getStockById(@PathVariable String id) {
    return stockService.getStockById(id).orElse(null);
  }

  @PostMapping
  public ResponseEntity<Stock> createStock(@RequestBody RequestStockDTO data) {
    Stock createdStock = stockService.createStock(data);
    return ResponseEntity.ok(createdStock);
  }

  @PutMapping("/{id}")
  public Stock updateStock(@PathVariable String id, @RequestBody RequestStockDTO data) {
    return stockService.updateStock(id, data).orElse(null);
  }

  @DeleteMapping("/{id}")
  public void deleteStock(@PathVariable String id) {
    stockService.deleteStock(id);
  }

}
