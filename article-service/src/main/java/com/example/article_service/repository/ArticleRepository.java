package com.example.article_service.repository;

import com.example.article_service.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByNomContaining(String nom);
    List<Article> findByPrixBetween(Double prixMin, Double prixMax);
}