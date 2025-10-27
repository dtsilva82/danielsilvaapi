package br.edu.infnet.danielsilvaapi.model.domain;

public class Midia{
	
	private Integer id;

    private String titulo;
    private String console;
    private String desenvolvedora;
    private String genero;
    private String anoLancamento;
	private String tipoMidia;
    private String regiao;

    private String estadoConservacao;
    private Boolean completoNaCaixa;
    private Integer quantidadeEmEstoque;
    private String observacoes;

    private Double precoCusto;
    private Double precoVenda;

    
    @Override
    public String toString() {
    	return String.format(
    			"%05d | Título: %-50s | Console: %-20s | Desenvolvedora: %-20s | Genero: %s | Ano de Lançamento: %s | Tipo da mídia: %s | Região: %s | Estado de Conservação %s | Com caixa: %-3s | Quantidade: %d | Preço: R$%6.2f | Observações: %-100s",
    			id,
    			titulo,
    			console,
    			desenvolvedora,
    			genero,
    			anoLancamento,
    			tipoMidia,
    			regiao,
    			estadoConservacao,
    			completoNaCaixa ? "Sim" : "Não",
    			quantidadeEmEstoque,
    			precoVenda,
    			observacoes
    	);
    }
        public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getConsole() {
		return console;
	}
	public void setConsole(String console) {
		this.console = console;
	}
	public String getDesenvolvedora() {
		return desenvolvedora;
	}
	public void setDesenvolvedora(String desenvolvedora) {
		this.desenvolvedora = desenvolvedora;
	}
		public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getAnoLancamento() {
		return anoLancamento;
	}
	public void setAnoLancamento(String anoLancamento) {
		this.anoLancamento = anoLancamento;
	}
	public String getTipoMidia() {
		return tipoMidia;
	}
	public void setTipoMidia(String tipoMidia) {
		this.tipoMidia = tipoMidia;
	}
	public String getRegiao() {
		return regiao;
	}
	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}
	public String getEstadoConservacao() {
		return estadoConservacao;
	}
	public void setEstadoConservacao(String estadoConservacao) {
		this.estadoConservacao = estadoConservacao;
	}
	public Boolean getCompletoNaCaixa() {
		return completoNaCaixa;
	}
	public void setCompletoNaCaixa(Boolean completoNaCaixa) {
		this.completoNaCaixa = completoNaCaixa;
	}
	public Integer getQuantidadeEmEstoque() {
		return quantidadeEmEstoque;
	}
	public void setQuantidadeEmEstoque(Integer quantidadeEmEstoque) {
		this.quantidadeEmEstoque = quantidadeEmEstoque;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	public Double getPrecoCusto() {
		return precoCusto;
	}
	public void setPrecoCusto(Double precoCusto) {
		this.precoCusto = precoCusto;
	}
	public Double getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

}
