package br.edu.infnet.danielsilvaapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.danielsilvaapi.dto.PedidoRequest;
import br.edu.infnet.danielsilvaapi.dto.PedidoResponse;
import br.edu.infnet.danielsilvaapi.model.domain.Pedido;
import br.edu.infnet.danielsilvaapi.model.service.PedidoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> realizarPedido(@Valid @RequestBody PedidoRequest request) {
              
        PedidoResponse response = pedidoService.realizarPedidoEMapear(request);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping
    public ResponseEntity<List<Pedido>> obterLista() {
        
        List<Pedido> pedidos = pedidoService.obterLista();
        
        return ResponseEntity.ok(pedidos);
    }
}