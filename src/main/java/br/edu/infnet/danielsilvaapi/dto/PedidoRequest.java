package br.edu.infnet.danielsilvaapi.dto;

import java.util.List;

public class PedidoRequest {

    private List<ItemPedidoRequest> itens;

    public List<ItemPedidoRequest> getItens() { 
    		return itens;
    	}
    public void setItens(List<ItemPedidoRequest> itens) {
    		this.itens = itens; 
    	}
}