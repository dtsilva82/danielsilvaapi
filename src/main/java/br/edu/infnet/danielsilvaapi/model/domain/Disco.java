package br.edu.infnet.danielsilvaapi.model.domain;

public class Disco extends Jogo{
	
	private DiscoEstado discoEstado;              
    private DiscoCapa discoCapa;              
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
    			this.discoEstado.getRotulo(),
    			this.discoCapa.getRotulo(),
    			this.discoConteudoExtra.getRotulo()
    	);
    }


    public String getDiscoEstado() {
        return this.discoEstado.getRotulo(); 
    }

	public void setDiscoEstado(DiscoEstado discoEstado) {
		this.discoEstado = discoEstado;
	}

	public String getDiscoCapa() {
	    return this.discoCapa.getRotulo();
	}
	
	public void setDiscoCapa(DiscoCapa discoCapa) {
	    this.discoCapa = discoCapa;
	}

	public String getDiscoConteudoExtra() {
		return this.discoConteudoExtra.getRotulo();
	}

	public void setDiscoConteudoExtra(DiscoConteudoExtra discoConteudoExtra) {
		this.discoConteudoExtra = discoConteudoExtra;
	}
}
