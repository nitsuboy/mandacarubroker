package com.mandacarubroker.service;

import com.mandacarubroker.domain.stock.RequestStockDTO;
import com.mandacarubroker.domain.stock.Stock;
import com.mandacarubroker.domain.stock.StockRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 * Service Stock.
 */
@Service
public class StockService {

  private final StockRepository stockRepository;

  public StockService(StockRepository stockRepository) {
    this.stockRepository = stockRepository;
  }

  public List<Stock> getAllStocks() {
    return stockRepository.findAll();
  }

  /**
   * Buscar Stock.
   */
  public Optional<Stock> getStockById(String id) {
    return stockRepository.findById(id);
  }

  /**
   * Criar Stock.
   */
  public Stock createStock(RequestStockDTO data) {
    Stock newStock = new Stock(data);
    validateRequestStockDTO(data);
    return stockRepository.save(newStock);
  }

  /**
   * Atualizar Stock.
   */
  public Optional<Stock> updateStock(String id, RequestStockDTO data) {
    Stock updatedStock = new Stock(data);
    validateRequestStockDTO(data);
    return stockRepository.findById(id)
        .map(stock -> {
          stock.setSymbol(updatedStock.getSymbol());
          stock.setCompanyName(updatedStock.getCompanyName());
          stock.setPrice(updatedStock.getPrice());

          return stockRepository.save(stock);
        });
  }

  /**
   * Excluir Stock.
   */
  public void deleteStock(String id) {
    stockRepository.deleteById(id);
  }

  /**
   * Validar Stock.
   */
  public static void validateRequestStockDTO(RequestStockDTO data) {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();
      Set<ConstraintViolation<RequestStockDTO>> violations = validator.validate(data);

      if (!violations.isEmpty()) {
        StringBuilder errorMessage = new StringBuilder("Validation failed. Details: ");

        for (ConstraintViolation<RequestStockDTO> violation : violations) {
          errorMessage.append(
              String.format("[%s: %s], ", violation.getPropertyPath(), violation.getMessage()));
        }

        errorMessage.delete(errorMessage.length() - 2, errorMessage.length());

        throw new ConstraintViolationException(errorMessage.toString(), violations);
      }
    }
  }

}
