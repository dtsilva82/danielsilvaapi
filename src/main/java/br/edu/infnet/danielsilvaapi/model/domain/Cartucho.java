package br.edu.infnet.danielsilvaapi.model.domain;

import jakarta.persistence.Entity;

@Entity
public class Cartucho extends Jogo {

    private CartuchoConservacao cartuchoConservacao;
    private boolean possuiCaixaOriginal;
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
    			cartuchoRegiao,
    			this.cartuchoConservacao.getRotulo(),
    			getPossuiCaixaOriginal()
    		);
    }
    
    public String getCartuchoConservacao() {
		return cartuchoConservacao.getRotulo();
	}
	public void setCartuchoConservacao(CartuchoConservacao cartuchoConservacao) {
		this.cartuchoConservacao = cartuchoConservacao;
	}
	public boolean isPossuiCaixaOriginal() {
		return possuiCaixaOriginal;
	}
	public String getPossuiCaixaOriginal() {
	    return this.possuiCaixaOriginal ? "Caixa Original" : "Não acompanha caixa";
	}
	public void setPossuiCaixaOriginal(boolean possuiCaixaOriginal) {
		this.possuiCaixaOriginal = possuiCaixaOriginal;
	}

	public String getCartuchoRegiao() {
		return cartuchoRegiao.getRotulo(); 
	}
	public void setCartuchoRegiao(CartuchoRegiao cartuchoRegiao) { 
		this.cartuchoRegiao = cartuchoRegiao;
	}
}
