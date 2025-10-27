package br.edu.infnet.danielsilvaapi.model.domain;

public enum CartuchoConservacao {
	
	LACRADO_COLECIONADOR("Lacrado/Colecionador"),
    REGULAR("Muitas marcas de uso"),
    BOM("Poucas marcas de uso"),
    OTIMO("Nehuma marca de uso");
	
	private final String rotulo;
    
	CartuchoConservacao(String rotulo) {
        this.rotulo = rotulo;
    }

    public String getRotulo() {
        return rotulo;
    }
}
