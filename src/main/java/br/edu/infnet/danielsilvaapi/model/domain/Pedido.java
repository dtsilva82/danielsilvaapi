package br.edu.infnet.danielsilvaapi.model.domain;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TB_PEDIDO")
@SequenceGenerator(name = "pedido_seq", sequenceName = "PEDIDO_SEQ", allocationSize = 1)
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_seq")
    private Integer id;

    private LocalDateTime dataPedido = LocalDateTime.now();

    private double valorTotal;
    
    @ElementCollection
    @CollectionTable(name = "TB_ITENS_PEDIDO", joinColumns = @JoinColumn(name = "pedido_id"))
    @Embedded 
    private List<ItemPedido> itensPedido;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public LocalDateTime getDataPedido() { return dataPedido; }
    public void setDataPedido(LocalDateTime dataPedido) { this.dataPedido = dataPedido; }
    public double getValorTotal() { return valorTotal; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }
    public List<ItemPedido> getItensPedido() { return itensPedido; }
    public void setItensPedido(List<ItemPedido> itensPedido) { this.itensPedido = itensPedido; }
}