package com.example.article_service.service;

import com.example.article_service.model.Article;
import com.example.article_service.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    
    @Autowired
    private ArticleRepository articleRepository;
    
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }
    
    public Optional<Article> getArticleById(Long id) {
        return articleRepository.findById(id);
    }
    
    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }
    
    public Article updateArticle(Long id, Article articleDetails) {
        Article article = articleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Article non trouvé avec l'id: " + id));
        
        article.setNom(articleDetails.getNom());
        article.setDescription(articleDetails.getDescription());
        article.setPrix(articleDetails.getPrix());
        article.setQuantite(articleDetails.getQuantite());
        
        return articleRepository.save(article);
    }
    
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }
    
    public List<Article> searchByNom(String nom) {
        return articleRepository.findByNomContaining(nom);
    }
}