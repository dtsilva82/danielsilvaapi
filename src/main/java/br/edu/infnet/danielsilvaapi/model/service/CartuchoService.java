package br.edu.infnet.danielsilvaapi.model.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import br.edu.infnet.danielsilvaapi.client.RawgApiClient;
import br.edu.infnet.danielsilvaapi.dto.GameScores;
import br.edu.infnet.danielsilvaapi.exceptions.JogoNaoEncontradoException;
import br.edu.infnet.danielsilvaapi.interfaces.CrudService;
import br.edu.infnet.danielsilvaapi.model.domain.Cartucho;
import br.edu.infnet.danielsilvaapi.model.domain.Jogo;
import br.edu.infnet.danielsilvaapi.model.repository.CartuchoRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class CartuchoService implements CrudService<Cartucho, Integer> { 
	
	private final CartuchoRepository cartuchoRepository;
	private final RawgApiClient rawgApiClient;
	private final EntityManager entityManager;
	
	public CartuchoService(CartuchoRepository cartuchoRepository, RawgApiClient rawgApiClient, EntityManager entityManager) {
		this.cartuchoRepository = cartuchoRepository;
		this.rawgApiClient = rawgApiClient;
		this.entityManager = entityManager;
	}
	
		
	@Override
	public Cartucho incluir(Cartucho cartucho) {
		
		validarCamposObrigatorios(cartucho);
		tratarObservacoes(cartucho);
		
		String termoBusca = cartucho.getTitulo() + " " + cartucho.getConsole();
		GameScores scores = rawgApiClient.buscarScores(termoBusca).block();
		
		cartucho.setLastApiSync(LocalDateTime.now());
		
		if (scores != null) {
            cartucho.setMetacriticScore(scores.getMetacriticScore());
            cartucho.setUserScore(scores.getUserScore()); 
            cartucho.setUserRatingText(scores.getUserRatingText());
        }

		return cartuchoRepository.save(cartucho); 
	}

	@Override
	public List<Cartucho> obterLista() {
		return cartuchoRepository.findAll();
	}
	
	@Override
	public Cartucho obterPorID(Integer id) {
		
		if(id == null || id <= 0) {
			throw new IllegalArgumentException("O ID utilizado a busca de Cartucho não pode ser nulo");
		}
		
		Optional<Cartucho> cartucho = cartuchoRepository.findById(id);
		
		return cartucho.orElseThrow(() -> 
		    new JogoNaoEncontradoException("O cartucho com o ID ["+id+"] não foi encontrado")
		);
	}

	@Transactional
	@Override
	public Cartucho alterar(Integer id, Cartucho cartucho) {
		
		cartucho.setId(id);
		
		Cartucho cartuchoExistente = obterPorID(id);
				
		validarCamposObrigatorios(cartucho);
		tratarObservacoes(cartucho);
		
		cartuchoExistente.setTitulo(cartucho.getTitulo());
        cartuchoExistente.setConsole(cartucho.getConsole());
        cartuchoExistente.setDesenvolvedora(cartucho.getDesenvolvedora());
        cartuchoExistente.setGenero(cartucho.getGenero());
        cartuchoExistente.setAnoLancamento(cartucho.getAnoLancamento());
        cartuchoExistente.setQuantidadeEmEstoque(cartucho.getQuantidadeEmEstoque());
        cartuchoExistente.setPrecoCusto(cartucho.getPrecoCusto());
        cartuchoExistente.setPrecoVenda(cartucho.getPrecoVenda());
        cartuchoExistente.setObservacoes(cartucho.getObservacoes());

        cartuchoExistente.setCartuchoRegiao(cartucho.getCartuchoRegiaoEnum());
        cartuchoExistente.setCartuchoConservacao(cartucho.getCartuchoConservacaoEnum());
        cartuchoExistente.setPossuiCaixaOriginal(cartucho.isPossuiCaixaOriginal());
   		
        return entityManager.merge(cartuchoExistente);
	}


	@Override
	public void excluir(Integer id) {
        
        if(id == null || id <= 0) {
			throw new IllegalArgumentException("O ID para exclusão não pode ser nulo ou zero");
		}
        
        obterPorID(id); 
		cartuchoRepository.deleteById(id);
	}
	
	
	private void validarCamposObrigatorios(Jogo jogo) {
         if (jogo.getTitulo() == null || jogo.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("O título do jogo é obrigatório.");
        }
        if (jogo.getConsole() == null || jogo.getConsole().trim().isEmpty()) {
            throw new IllegalArgumentException("O console é obrigatório.");
        }
        if (jogo.getDesenvolvedora() == null || jogo.getDesenvolvedora().trim().isEmpty()) {
            throw new IllegalArgumentException("A desenvolvedora é obrigatória.");
        }
        if (jogo.getGenero() == null || jogo.getGenero().trim().isEmpty()) {
            throw new IllegalArgumentException("O gênero é obrigatório.");
        }
        if (jogo.getAnoLancamento() == null || jogo.getAnoLancamento().trim().isEmpty()) {
            throw new IllegalArgumentException("O ano de lançamento é obrigatório.");
        }
        
        if (jogo.getQuantidadeEmEstoque() == null || jogo.getQuantidadeEmEstoque() < 0) {
            throw new IllegalArgumentException("A quantidade em estoque é obrigatória e deve ser maior ou igual a zero.");
        }
        if (jogo.getPrecoCusto() == null || jogo.getPrecoCusto() < 0) {
             throw new IllegalArgumentException("O preço de custo é obrigatório e não pode ser negativo.");
        }
        if (jogo.getPrecoVenda() == null || jogo.getPrecoVenda() < 0) {
             throw new IllegalArgumentException("O preço de venda é obrigatório e não pode ser negativo.");
        }
    }
    
    private void tratarObservacoes(Jogo jogo) {	
        String obs = jogo.getObservacoes();
        if (obs == null || obs.trim().isEmpty()) {
            jogo.setObservacoes("");
        }
    }


}