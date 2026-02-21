package com.example.article_service.consumer;

import com.example.article_service.event.StockEvent;
import com.example.article_service.model.Article;
import com.example.article_service.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class StockEventConsumer {
    
    @Autowired
    private ArticleService articleService;
    
    @KafkaListener(topics = "stock-events", groupId = "article-group")
    public void consumeStockEvent(StockEvent event) {
        System.out.println("=== Événement de stock reçu ===");
        System.out.println("Article ID: " + event.getArticleId());
        System.out.println("Type: " + event.getTypeMouvement());
        System.out.println("Quantité: " + event.getQuantite());
        
        try {
            Article article = articleService.getArticleById(event.getArticleId())
                .orElseThrow(() -> new RuntimeException("Article non trouvé: " + event.getArticleId()));
            
            int ancienneQuantite = article.getQuantite();
            
            if ("ENTREE".equals(event.getTypeMouvement())) {
                article.setQuantite(ancienneQuantite + event.getQuantite());
                System.out.println("ENTREE: " + ancienneQuantite + " -> " + article.getQuantite());
            } else if ("SORTIE".equals(event.getTypeMouvement())) {
                article.setQuantite(ancienneQuantite - event.getQuantite());
                System.out.println("SORTIE: " + ancienneQuantite + " -> " + article.getQuantite());
            }
            
            articleService.updateArticle(article.getId(), article);
            System.out.println("Article mis à jour avec succès");
            
        } catch (Exception e) {
            System.err.println("Erreur lors du traitement: " + e.getMessage());
        }
    }
}