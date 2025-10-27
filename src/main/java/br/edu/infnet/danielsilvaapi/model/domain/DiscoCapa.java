package br.edu.infnet.danielsilvaapi.model.domain;

public enum DiscoCapa {
	
	ORIGINAL("Capa Original"),
    REPRODUCAO("Capa Genérica");

    private final String rotulo; 

    DiscoCapa(String rotulo) {
        this.rotulo = rotulo;
    }

    public String getRotulo() {
        return rotulo;
    }
}