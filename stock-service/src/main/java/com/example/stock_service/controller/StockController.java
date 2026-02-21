package com.example.stock_service.controller;

import com.example.stock_service.model.Stock;
import com.example.stock_service.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    
    @Autowired
    private StockService stockService;
    
    @GetMapping
    public List<Stock> getAllMouvements() {
        return stockService.getAllMouvements();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Stock> getMouvementById(@PathVariable Long id) {
        return stockService.getMouvementById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/article/{articleId}")
    public List<Stock> getMouvementsByArticleId(@PathVariable Long articleId) {
        return stockService.getMouvementsByArticleId(articleId);
    }
    
    @PostMapping
    public ResponseEntity<Stock> createMouvement(@RequestBody Stock stock) {
        Stock createdMouvement = stockService.createMouvement(stock);
        return new ResponseEntity<>(createdMouvement, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMouvement(@PathVariable Long id) {
        try {
            stockService.deleteMouvement(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}