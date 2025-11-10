package br.edu.infnet.danielsilvaapi.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ItemPedido {

    @Column(name = "jogo_id")
    private Integer jogoId;
    
    @Column(name = "quantidade_comprada")
    private Integer quantidadeComprada;

    public ItemPedido() {}

    public ItemPedido(Integer jogoId, Integer quantidadeComprada) {
        this.jogoId = jogoId;
        this.quantidadeComprada = quantidadeComprada;
    }
    
    public Integer getJogoId() { 
    		return jogoId; 
    	}
    public void setJogoId(Integer jogoId) { 
    		this.jogoId = jogoId;
    	}
    public Integer getQuantidadeComprada() {
    		return quantidadeComprada; 
    	}
    public void setQuantidadeComprada(Integer quantidadeComprada) {
    		this.quantidadeComprada = quantidadeComprada; 
    	}
}