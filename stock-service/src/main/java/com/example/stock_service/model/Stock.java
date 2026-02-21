package com.example.stock_service.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long articleId;  // Référence vers l'article dans article-service
    
    @Column(nullable = false)
    private Integer quantite;
    
    @Column(nullable = false)
    private String typeMouvement;  // "ENTREE" ou "SORTIE"
    
    @Column(nullable = false)
    private String commentaire;
    
    @Column(name = "date_mouvement")
    private LocalDateTime dateMouvement;
    
    // Constructeurs
    public Stock() {}
    
    public Stock(Long articleId, Integer quantite, String typeMouvement, String commentaire) {
        this.articleId = articleId;
        this.quantite = quantite;
        this.typeMouvement = typeMouvement;
        this.commentaire = commentaire;
        this.dateMouvement = LocalDateTime.now();
    }
    
    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getArticleId() { return articleId; }
    public void setArticleId(Long articleId) { this.articleId = articleId; }
    
    public Integer getQuantite() { return quantite; }
    public void setQuantite(Integer quantite) { this.quantite = quantite; }
    
    public String getTypeMouvement() { return typeMouvement; }
    public void setTypeMouvement(String typeMouvement) { this.typeMouvement = typeMouvement; }
    
    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }
    
    public LocalDateTime getDateMouvement() { return dateMouvement; }
    public void setDateMouvement(LocalDateTime dateMouvement) { this.dateMouvement = dateMouvement; }
}