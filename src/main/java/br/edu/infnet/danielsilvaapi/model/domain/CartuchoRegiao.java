package br.edu.infnet.danielsilvaapi.model.domain;

public enum CartuchoRegiao {

	NTSC_US("NTSC - América do Norte"),
    PAL_EU("PAL - Europa"),
    NTSC_J("NTSC - Japão"),
    REGION_FREE("Region Free");

    private final String rotulo;

    CartuchoRegiao(String rotulo) {
        this.rotulo = rotulo;
    }

    public String getRotulo() {
        return rotulo;
    }
}
