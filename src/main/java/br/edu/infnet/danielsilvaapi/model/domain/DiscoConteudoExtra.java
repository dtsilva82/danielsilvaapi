package br.edu.infnet.danielsilvaapi.model.domain;

public enum DiscoConteudoExtra {

	NAO_POSSUI("Não Possui"),
    DLC("Possui DLC)"), 
    SOUNDTRACK_CD("Soundtrack (CD Físico)"),
    ARTBOOK_MINI("Artbook / Mini-Livro");
    
    private final String rotulo; 

    DiscoConteudoExtra(String rotulo) {
        this.rotulo = rotulo;
    }

    public String getRotulo() {
        return rotulo;
    }
}
