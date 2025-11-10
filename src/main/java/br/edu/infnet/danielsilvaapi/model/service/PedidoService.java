package br.edu.infnet.danielsilvaapi.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import br.edu.infnet.danielsilvaapi.dto.ItemPedidoRequest;
import br.edu.infnet.danielsilvaapi.dto.PedidoRequest;
import br.edu.infnet.danielsilvaapi.dto.PedidoResponse;
import br.edu.infnet.danielsilvaapi.exceptions.JogoNaoEncontradoException;
import br.edu.infnet.danielsilvaapi.model.domain.Jogo;
import br.edu.infnet.danielsilvaapi.model.domain.ItemPedido;
import br.edu.infnet.danielsilvaapi.model.domain.Pedido;
import br.edu.infnet.danielsilvaapi.model.repository.PedidoRepository;
import br.edu.infnet.danielsilvaapi.model.repository.DiscoRepository;
import br.edu.infnet.danielsilvaapi.model.repository.CartuchoRepository;
import jakarta.transaction.Transactional;

@Service
public class PedidoService {
    
    private final PedidoRepository pedidoRepository;
    private final DiscoService discoService; 
    private final CartuchoService cartuchoService; 
    private final DiscoRepository discoRepository; 
    private final CartuchoRepository cartuchoRepository; 

    public PedidoService(
        PedidoRepository pedidoRepository, 
        DiscoService discoService, 
        CartuchoService cartuchoService,
        DiscoRepository discoRepository,
        CartuchoRepository cartuchoRepository
    ) {
        this.pedidoRepository = pedidoRepository;
        this.discoService = discoService;
        this.cartuchoService = cartuchoService;
        this.discoRepository = discoRepository;
        this.cartuchoRepository = cartuchoRepository;
    }

    private Jogo buscarJogo(Integer id) throws JogoNaoEncontradoException {
        try {
            return discoService.obterPorID(id);
        } catch (JogoNaoEncontradoException e) {
            return cartuchoService.obterPorID(id);
        }
    }

    public PedidoResponse realizarPedidoEMapear(PedidoRequest request) {
         Pedido pedidoSalvo = realizarPedido(request);
         return mapearParaResponse(pedidoSalvo);
    }
    
    @Transactional
    public Pedido realizarPedido(PedidoRequest request) throws JogoNaoEncontradoException {
        double total = 0.0;
        List<ItemPedido> itensPersistencia = new ArrayList<>();

        for (ItemPedidoRequest itemRequest : request.getItens()) {
            Integer id = itemRequest.getJogoId();
            Integer quantidade = itemRequest.getQuantidadeComprada();
            
            if (quantidade == null || quantidade <= 0) {
                 throw new IllegalArgumentException("A quantidade comprada para o Jogo ID " + id + " deve ser positiva.");
            }

            Jogo jogo = buscarJogo(id); 
            
            if (jogo.getQuantidadeEmEstoque() < quantidade) {
                throw new IllegalArgumentException(
                    "Estoque insuficiente para " + jogo.getTitulo() + 
                    ". Estoque atual: " + jogo.getQuantidadeEmEstoque() + 
                    ", Quantidade pedida: " + quantidade
                );
            }
            
            total += (jogo.getPrecoVenda() * quantidade);
            
            itensPersistencia.add(new ItemPedido(id, quantidade));
        }
        
        Pedido pedido = new Pedido();
        pedido.setItensPedido(itensPersistencia); 
        pedido.setValorTotal(total);
        
        for (ItemPedidoRequest itemRequest : request.getItens()) {
            Integer id = itemRequest.getJogoId();
            Integer quantidade = itemRequest.getQuantidadeComprada();
            
            try {
                discoService.atualizarEstoque(id, quantidade); 
            } catch (JogoNaoEncontradoException e) {
                cartuchoService.atualizarEstoque(id, quantidade); 
            }
        }
        
        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        return pedidoSalvo;
    }
    
    private PedidoResponse mapearParaResponse(Pedido pedido) {
        
        List<PedidoResponse.ItemDetalhadoResponse> itensResponse = pedido.getItensPedido().stream()
            .map(item -> {
                Jogo jogoCompleto;
                
                try {
                    jogoCompleto = buscarJogo(item.getJogoId());
                } catch (JogoNaoEncontradoException e) {
                    throw new RuntimeException("Falha ao mapear item: Jogo ID " + item.getJogoId() + " não foi encontrado no catálogo.", e);
                }
                
                double precoUnitario = jogoCompleto.getPrecoVenda(); 

                return new PedidoResponse.ItemDetalhadoResponse(
                    jogoCompleto.getId(),
                    jogoCompleto.getTitulo(),
                    jogoCompleto.getConsole(),
                    jogoCompleto.getTipoMidia(),
                    precoUnitario,
                    item.getQuantidadeComprada()
                );
            })
            .collect(Collectors.toList());

        return new PedidoResponse(
            pedido.getId(),
            pedido.getDataPedido(),
            pedido.getValorTotal(),
            itensResponse
        );
    }
    
    public List<Jogo> buscarPorTituloParcial(String termo) {
        List<Jogo> resultados = new ArrayList<>(discoRepository.findByTituloParcial(termo));
        resultados.addAll(cartuchoRepository.findByTituloParcial(termo));
        return resultados;
    }

    public List<Pedido> obterLista() {
        return pedidoRepository.findAll();
    }
}