package br.edu.infnet.danielsilvaapi.model.domain;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@JsonTypeInfo(
 use = JsonTypeInfo.Id.NAME, 
 include = JsonTypeInfo.As.PROPERTY, 
 property = "tipoMidia"
)
@JsonSubTypes({
 @Type(value = Disco.class, name = "disco"),
 @Type(value = Cartucho.class, name = "cartucho")
})

@MappedSuperclass
@SequenceGenerator(name = "jogo_seq", sequenceName = "JOGO_SEQ", allocationSize = 1)
public abstract class Jogo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jogo_seq")
	private Integer id;
	
	@NotNull(message = "O console do jogo é obrigatório")
	private String console;
	@NotNull(message = "O título do jogo é obrigatório")
	private String titulo;
	@NotNull(message = "A desenvolvedora do jogo é obrigatório")
    private String desenvolvedora;
	@NotNull(message = "O genero do jogo é obrigatório")
	private String genero;
	@NotNull(message = "O ano de lançamento do jogo é obrigatório")
    private String anoLancamento;
	@Min(value=0, message = "O estoque é obrigatório e deve ser 0 ou maior")
    private int quantidadeEmEstoque;
	@Min(value=0, message = "O preço de custo é obrigatório e deve ser 0 ou maior")
    private double precoCusto;
	@Min(value=0, message = "O preço de venda é obrigatório e deve ser 0 ou maior")
    private double precoVenda;
    private String observacoes;
    public abstract String getTipoMidia();
    private Integer metacriticScore; 
    private Double userScore;
    private String userRatingText;
    private LocalDateTime lastApiSync;
    
    @Override
    public String toString() {
    	String baseInfo = String.format(
    			"%05d | Título: %-50s | Console: %-20s | Desenvolvedora: %-20s | Gênero: %s | Ano: %s | Qtd: %d | Preço: R$%6.2f | Obs: %s",
    			id,
    			titulo,
    			console,
    			desenvolvedora,
    			genero,
    			anoLancamento,
    			quantidadeEmEstoque,
    			precoVenda,
    			observacoes
    	);
        
        String scoreInfo = String.format(
                "\n        | Metacritic: %s | User Score(RAWG): %s | Classificação(RAWG): %s",
                this.getMetacriticScoreFormatado(),
                this.getUserScoreFormatado(),
                this.getUserRatingTextFormatado()
        );
        
        return baseInfo + scoreInfo;
    }

    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getConsole() {
		return console;
	}
	public void setConsole(String console) {
		this.console = console;
	}
	public String getDesenvolvedora() {
		return desenvolvedora;
	}
	public void setDesenvolvedora(String desenvolvedora) {
		this.desenvolvedora = desenvolvedora;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getAnoLancamento() {
		return anoLancamento;
	}
	public void setAnoLancamento(String anoLancamento) {
		this.anoLancamento = anoLancamento;
	}
	public Integer getQuantidadeEmEstoque() {
		return quantidadeEmEstoque;
	}
	public void setQuantidadeEmEstoque(Integer quantidadeEmEstoque) {
		this.quantidadeEmEstoque = quantidadeEmEstoque;
	}
	public Double getPrecoCusto() {
		return precoCusto;
	}
	public void setPrecoCusto(Double precoCusto) {
		this.precoCusto = precoCusto;
	}
	public Double getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	@JsonIgnore
	public Integer getMetacriticScore() {
		return metacriticScore;
	}

	public void setMetacriticScore(Integer metacriticScore) {
		this.metacriticScore = metacriticScore;
	}
	
	@JsonIgnore
	public Double getUserScore() {
		return userScore;
	}

	public void setUserScore(Double userScore) {
		this.userScore = userScore;
	}
	
	@JsonIgnore 
    public String getUserRatingText() {
        return userRatingText;
    }

    public void setUserRatingText(String userRatingText) {
        this.userRatingText = userRatingText;
    }
	
	@JsonProperty("metacriticScore")
	public String getMetacriticScoreFormatado() {
        if (metacriticScore == null) {
            return "N/A";
        }
        return String.valueOf(metacriticScore);
    }
    
	@JsonProperty("RAWG_userScore")
    public String getUserScoreFormatado() {
        if (userScore == null) {
            return "N/A";
        }
        return String.format("%.1f", userScore); 
    }
    
	@JsonProperty("RAWG_userClassification")
    public String getUserRatingTextFormatado() {
        if (userRatingText == null || userRatingText.trim().isEmpty()) {
            return "N/A";
        }
        
        switch (userRatingText.toLowerCase()) {
            case "exceptional":
                return "Excepcional";
            case "recommended":
                return "Recomendado";
            case "meh":
                return "Mediano";
            case "skip":
                return "Não recomendado";
            default:
                return userRatingText;
        }
    }
	
	@JsonIgnore 
    public LocalDateTime getLastApiSync() {
        return lastApiSync;
    }

    public void setLastApiSync(LocalDateTime lastApiSync) {
        this.lastApiSync = lastApiSync;
    }
    
    @JsonProperty("lastApiSync") 
    public String getLastApiSyncFormatado() {
        if (lastApiSync == null) {
            return "N/A";
        }
        
        return lastApiSync.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

}
