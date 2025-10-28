package br.edu.infnet.danielsilvaapi.dto;

public class GameScores {
    private final Integer metacriticScore;
    private final Double userScore;
    private final String userRatingText;
    
    public GameScores(Integer metacriticScore, Double userScore, String userRatingText) {
        this.metacriticScore = metacriticScore;
        this.userScore = userScore;
        this.userRatingText = userRatingText;
    }
    
    public Integer getMetacriticScore() {
        return metacriticScore;
    }
    public Double getUserScore() {
        return userScore;
    }
    
    public String getUserRatingText() {
        return userRatingText;
    }
}