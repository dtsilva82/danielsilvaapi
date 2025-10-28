package br.edu.infnet.danielsilvaapi.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RawgGameResult {
	
	@JsonProperty("metacritic") 
    private Integer metacriticScore;
    
    @JsonProperty("rating")
    private Double userScore;
    
    @JsonProperty("ratings")
    private List<RawgRating> ratings;

    public Integer getMetacriticScore() {
        return metacriticScore;
    }
    public void setMetacriticScore(Integer metacriticScore) {
        this.metacriticScore = metacriticScore;
    }

    public Double getUserScore() {
        return userScore;
    }
    public void setUserScore(Double userScore) {
        this.userScore = userScore;
    }
	public List<RawgRating> getRatings() {
		return ratings;
	}
	public void setRatings(List<RawgRating> ratings) {
		this.ratings = ratings;
	}
}