package com.example.stock_service.service;

import com.example.stock_service.model.Stock;
import com.example.stock_service.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    
    @Autowired
    private StockRepository stockRepository;
    
    public List<Stock> getAllMouvements() {
        return stockRepository.findAll();
    }
    
    public Optional<Stock> getMouvementById(Long id) {
        return stockRepository.findById(id);
    }
    
    public List<Stock> getMouvementsByArticleId(Long articleId) {
        return stockRepository.findByArticleId(articleId);
    }
    
    public Stock createMouvement(Stock stock) {
        return stockRepository.save(stock);
    }
    
    public void deleteMouvement(Long id) {
        stockRepository.deleteById(id);
    }
}