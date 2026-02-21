package com.example.stock_service.repository;

import com.example.stock_service.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findByArticleId(Long articleId);
    List<Stock> findByTypeMouvement(String typeMouvement);
}