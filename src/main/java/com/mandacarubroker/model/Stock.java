package com.mandacarubroker.model;



import com.mandacarubroker.request.RequestStockDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Atual Stock.
 */
@Table(name = "stock")
@Entity(name = "stock")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Stock {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String symbol;
  private String companyName;
  private double price;

  /**
   * Criar Stock.
   */
  public Stock(RequestStockDTO requestStocksdto) {
    this.symbol = requestStocksdto.symbol();
    this.companyName = requestStocksdto.companyName();
    this.price = requestStocksdto.price();
  }
}