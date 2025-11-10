package br.edu.infnet.danielsilvaapi.dto;

public class ItemPedidoRequest {
    
    private Integer jogoId;
    private Integer quantidadeComprada;

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