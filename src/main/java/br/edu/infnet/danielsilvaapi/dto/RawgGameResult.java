package br.edu.infnet.danielsilvaapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RawgGameResult {
	
    @JsonProperty("metacritic")
    private Integer metacriticScore;

	public Integer getMetacriticScore() {
		return metacriticScore;
	}

	public void setMetacriticScore(Integer metacriticScore) {
		this.metacriticScore = metacriticScore;
	}
}