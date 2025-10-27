package br.edu.infnet.danielsilvaapi.model.domain;

public class Cartucho extends Jogo {

    private CartuchoConservacao cartuchoConservacao;
    private Boolean possuiCaixaOriginal;
    private String regiao;
	
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
    			regiao,
    			this.cartuchoConservacao.getRotulo(),
    			this.possuiCaixaOriginal.booleanValue() ? "Caixa Original" : "Não acompanha caixa"
    	);
    }
    
    public String getCartuchoConservacao() {
		return cartuchoConservacao.getRotulo();
	}
	public void setCartuchoConservacao(CartuchoConservacao cartuchoConservacao) {
		this.cartuchoConservacao = cartuchoConservacao;
	}
	public Boolean getPossuiCaixaOriginal() {
		return possuiCaixaOriginal;
	}
	public void setPossuiCaixaOriginal(Boolean possuiCaixaOriginal) {
		this.possuiCaixaOriginal = possuiCaixaOriginal;
	}
	public String getRegiao() {
		return regiao;
	}
	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}
    
    
}
