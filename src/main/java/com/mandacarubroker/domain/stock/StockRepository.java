package com.mandacarubroker.domain.stock;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório do Stock.
 */
@Repository
public interface StockRepository  extends JpaRepository<Stock, String> {
}
