package br.edu.infnet.danielsilvaapi.model.domain;

public class Cartucho extends Jogo {

    private CartuchoConservacao cartuchoConservacao;
    private Boolean possuiCaixaOriginal;
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
    			getStatusCaixa()
    	);
    }
    
    public String getCartuchoConservacao() {
		return cartuchoConservacao.getRotulo();
	}
	public void setCartuchoConservacao(CartuchoConservacao cartuchoConservacao) {
		this.cartuchoConservacao = cartuchoConservacao;
	}
	public String getStatusCaixa() {
        if (this.possuiCaixaOriginal != null && this.possuiCaixaOriginal) {
            return "Caixa Original";
        }
        return "Não acompanha caixa";
    }
	public void setPossuiCaixaOriginal(Boolean possuiCaixaOriginal) {
		this.possuiCaixaOriginal = possuiCaixaOriginal;
	}
	public String getCartuchoRegiao() {
		return cartuchoRegiao.getRotulo(); 
	}
	public void setCartuchoRegiao(CartuchoRegiao cartuchoRegiao) { 
		this.cartuchoRegiao = cartuchoRegiao;
	}
}
