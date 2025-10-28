package br.edu.infnet.danielsilvaapi.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "jogo_seq", sequenceName = "JOGO_SEQ", allocationSize = 1)
public class Cartucho extends Jogo {

	@Enumerated(EnumType.STRING)
	private CartuchoConservacao cartuchoConservacao;
    private boolean possuiCaixaOriginal;
    @Enumerated(EnumType.STRING)
    private CartuchoRegiao cartuchoRegiao;
	
    @Override
    public String getTipoMidia() {
        return "Cartucho";
    }
    
    @Override
    public String toString() {
    		return String.format(
    			"%s | Tipo da mídia: %s | Região: %s | Estado de Conservação %s | Caixa? %s",
    			super.toString(),
    			getTipoMidia(),
    			getCartuchoRegiaoRotulo(),
    	        getCartuchoConservacaoRotulo(),
    	        getPossuiCaixaOriginalRotulo()
    		);
    }
    
    
    
    @JsonIgnore
    public CartuchoConservacao getCartuchoConservacaoEnum() {
        return cartuchoConservacao;
    }
    public void setCartuchoConservacao(CartuchoConservacao cartuchoConservacao) {
        this.cartuchoConservacao = cartuchoConservacao;
    }

    public boolean isPossuiCaixaOriginal() {
        return possuiCaixaOriginal;
    }
    public void setPossuiCaixaOriginal(boolean possuiCaixaOriginal) {
        this.possuiCaixaOriginal = possuiCaixaOriginal;
    }

    @JsonIgnore
    public CartuchoRegiao getCartuchoRegiaoEnum() {
        return cartuchoRegiao; 
    }
    public void setCartuchoRegiao(CartuchoRegiao cartuchoRegiao) {  
        this.cartuchoRegiao = cartuchoRegiao;
    }
    
    @JsonProperty("cartuchoConservacao")
    public String getCartuchoConservacaoRotulo() {
        return cartuchoConservacao != null ? cartuchoConservacao.getRotulo() : "N/A";
    }

    @JsonProperty("cartuchoRegiao")
    public String getCartuchoRegiaoRotulo() {
        return cartuchoRegiao != null ? cartuchoRegiao.getRotulo() : "N/A"; 
    }
    
    @JsonProperty("possuiCaixaOriginal")
    public String getPossuiCaixaOriginalRotulo() {
        return this.possuiCaixaOriginal ? "Caixa Original" : "Não acompanha caixa";
    }
}