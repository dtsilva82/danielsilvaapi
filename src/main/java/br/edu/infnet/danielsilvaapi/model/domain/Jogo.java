package br.edu.infnet.danielsilvaapi.model.domain;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@JsonTypeInfo(
 use = JsonTypeInfo.Id.NAME, 
 include = JsonTypeInfo.As.PROPERTY, 
 property = "tipoMidia"
)
@JsonSubTypes({
 @Type(value = Disco.class, name = "disco"),
 @Type(value = Cartucho.class, name = "cartucho")
})

public abstract class Jogo {

	private Integer id;
	
	private String console;
	private String titulo;
    private String desenvolvedora;
    private String genero;
    private String anoLancamento;
    private Integer quantidadeEmEstoque;
    private Double precoCusto;
    private Double precoVenda;
    private String observacoes;
    public abstract String getTipoMidia();
    
    @Override
    public String toString() {
    	return String.format(
    			"%05d | Título: %-50s | Console: %-20s | Desenvolvedora: %-20s | Genero: %s | Ano de Lançamento: %s | Quantidade: %d | Preço: R$%6.2f | Observações: %s",
    			id,
    			titulo,
    			console,
    			desenvolvedora,
    			genero,
    			anoLancamento,
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
	public Integer getQuantidadeEmEstoque() {
		return quantidadeEmEstoque;
	}
	public void setQuantidadeEmEstoque(Integer quantidadeEmEstoque) {
		this.quantidadeEmEstoque = quantidadeEmEstoque;
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
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
    
    

}
