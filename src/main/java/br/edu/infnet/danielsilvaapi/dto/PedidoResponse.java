package br.edu.infnet.danielsilvaapi.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoResponse {
    
    private Integer id;
    private LocalDateTime dataPedido;
    private double valorTotal;
    
    private List<ItemDetalhadoResponse> itens; 

    public PedidoResponse(Integer id, LocalDateTime dataPedido, double valorTotal, List<ItemDetalhadoResponse> itens) {
        this.id = id;
        this.dataPedido = dataPedido;
        this.valorTotal = valorTotal;
        this.itens = itens;
    }

    public Integer getId() { return id; }
    public LocalDateTime getDataPedido() { return dataPedido; }
    public double getValorTotal() { return valorTotal; }
    public List<ItemDetalhadoResponse> getItens() { return itens; }

    public static class ItemDetalhadoResponse {
        private Integer jogoId;
        private String titulo;
        private String console;
        private String tipoMidia;
        private double precoUnitario; 
        private Integer quantidadeComprada;

        public ItemDetalhadoResponse(Integer jogoId, String titulo, String console, String tipoMidia, double precoUnitario, Integer quantidadeComprada) {
            this.jogoId = jogoId;
            this.titulo = titulo;
            this.console = console;
            this.tipoMidia = tipoMidia;
            this.precoUnitario = precoUnitario;
            this.quantidadeComprada = quantidadeComprada;
        }

        public Integer getJogoId() { return jogoId; }
        public String getTitulo() { return titulo; }
        public String getConsole() { return console; }
        public String getTipoMidia() { return tipoMidia; }
        public double getPrecoUnitario() { return precoUnitario; } 
        public Integer getQuantidadeComprada() { return quantidadeComprada; }
    }
}