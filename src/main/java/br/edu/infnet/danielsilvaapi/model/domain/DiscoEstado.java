package br.edu.infnet.danielsilvaapi.model.domain;

public enum DiscoEstado {

	LACRADO_COLECIONADOR("Lacrado/Colecionador"),
    RISCO_BAIXO("Pocos Riscos (Leitura OK)"),
    RISCO_MODERADO("Riscos Moderados (Leitura OK)");
	
	private final String rotulo;
    
    DiscoEstado(String rotulo) {
        this.rotulo = rotulo;
    }

    public String getRotulo() {
        return rotulo;
    }
}


