package br.edu.infnet.danielsilvaapi.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "jogo_seq", sequenceName = "JOGO_SEQ", allocationSize = 1)
public class Disco extends Jogo{
	
	@Enumerated(EnumType.STRING)
	private DiscoEstado discoEstado;
	@Enumerated(EnumType.STRING)
    private DiscoCapa discoCapa;
	@Enumerated(EnumType.STRING)
    private DiscoConteudoExtra discoConteudoExtra;

    @Override
    public String getTipoMidia() {
        return "Disco";
    }
    
    @Override
    public String toString() {
    	return String.format(
    			"%s | Tipo da mídia: %s | Estado de Conservação: %s | Capa: %s | Conteúdo Extra? %s",
    			super.toString(),
    			getTipoMidia(),
    			getDiscoEstadoRotulo(),
    			getDiscoCapaRotulo(),
    			getDiscoConteudoExtraRotulo()
    	);
    }

    @JsonIgnore
    public DiscoEstado getDiscoEstadoEnum() {
        return this.discoEstado; 
    }
	public void setDiscoEstado(DiscoEstado discoEstado) {
		this.discoEstado = discoEstado;
	}

    @JsonIgnore
	public DiscoCapa getDiscoCapaEnum() {
	    return this.discoCapa;
	}
	public void setDiscoCapa(DiscoCapa discoCapa) {
	    this.discoCapa = discoCapa;
	}

    @JsonIgnore
	public DiscoConteudoExtra getDiscoConteudoExtraEnum() {
		return this.discoConteudoExtra;
	}
	public void setDiscoConteudoExtra(DiscoConteudoExtra discoConteudoExtra) {
		this.discoConteudoExtra = discoConteudoExtra;
	}
	
    @JsonProperty("discoEstado")
    public String getDiscoEstadoRotulo() {
        return this.discoEstado != null ? this.discoEstado.getRotulo() : "N/A"; 
    }

    @JsonProperty("discoCapa")
	public String getDiscoCapaRotulo() {
	    return this.discoCapa != null ? this.discoCapa.getRotulo() : "N/A";
	}

    @JsonProperty("discoConteudoExtra")
	public String getDiscoConteudoExtraRotulo() {
		return this.discoConteudoExtra != null ? this.discoConteudoExtra.getRotulo() : "N/A";
	}
    
}