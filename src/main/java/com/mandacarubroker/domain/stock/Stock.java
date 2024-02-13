package com.mandacarubroker.domain.stock;



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
    this.price = changePrice(requestStocksdto.price(), true);
  }

  /**
   * Mudar o valor do Stock.
   */
  public double changePrice(double amount, boolean increase) {
    if (increase) {
      if (amount < this.price) {
        return increasePrice(amount);
      } else {
        return decreasePrice(amount);
      }
    } else {
      if (amount > this.price) {
        return increasePrice(amount);
      } else {
        return this.decreasePrice(amount);
      }
    }
  }

  public double increasePrice(double amount) {
    return this.price + amount;
  }

  public double decreasePrice(double amount) {
    return this.price - amount;
  }

}