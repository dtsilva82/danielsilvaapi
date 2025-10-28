package br.edu.infnet.danielsilvaapi.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import br.edu.infnet.danielsilvaapi.dto.RawgSearchResponse;
import br.edu.infnet.danielsilvaapi.dto.GameScores; 
import reactor.core.publisher.Mono;

@Component
public class RawgApiClient {

    private final WebClient webClient;
    private final String apiKey;

    public RawgApiClient(@Value("${rawg.api.url}") String apiUrl,
                         @Value("${rawg.api.key}") String apiKey) {
        this.webClient = WebClient.builder()
            .baseUrl(apiUrl)
            .build();
        this.apiKey = apiKey;
    }

    public Mono<GameScores> buscarScores(String nomeJogo) {
        return webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/games")
                .queryParam("key", apiKey) 
                .queryParam("search", nomeJogo)
                .build())
            .retrieve()
            .bodyToMono(RawgSearchResponse.class)
            
            .map(response -> response.getResults().stream()
                .findFirst()
                .map(r -> {
                    Integer metacritic = r.getMetacriticScore();
                    
                    Double userScore = r.getUserScore(); 
                    
                    String ratingText = r.getRatings() != null && !r.getRatings().isEmpty() ? 
                                         r.getRatings().get(0).getTitle() : 
                                         null;
                    
                    return new GameScores(metacritic, userScore, ratingText);
                })
                .orElse(new GameScores(null, null, null)) 
            )
            .defaultIfEmpty(new GameScores(null, null, null)); 
    }
}