package br.edu.infnet.danielsilvaapi.model.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.infnet.danielsilvaapi.client.RawgApiClient;
import br.edu.infnet.danielsilvaapi.dto.GameScores;
import br.edu.infnet.danielsilvaapi.exceptions.JogoNaoEncontradoException;
import br.edu.infnet.danielsilvaapi.interfaces.CrudService;
import br.edu.infnet.danielsilvaapi.model.domain.Disco;
import br.edu.infnet.danielsilvaapi.model.domain.Jogo;
import br.edu.infnet.danielsilvaapi.model.repository.DiscoRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class DiscoService implements CrudService<Disco, Integer> { 
	
	private final DiscoRepository discoRepository;
	private final RawgApiClient rawgApiClient;
	private final EntityManager entityManager;
	
	public DiscoService(DiscoRepository discoRepository, RawgApiClient rawgApiClient, EntityManager entityManager) {
		this.discoRepository = discoRepository;
		this.rawgApiClient = rawgApiClient;
		this.entityManager = entityManager;
	}
	
		
	@Override
	public Disco incluir(Disco disco) {
		
		validarCamposObrigatorios(disco);
		tratarObservacoes(disco);
		
		String termoBusca = disco.getTitulo() + " " + disco.getConsole();
		GameScores scores = rawgApiClient.buscarScores(termoBusca).block();
		
		disco.setLastApiSync(LocalDateTime.now());
		
		if (scores != null) {
            disco.setMetacriticScore(scores.getMetacriticScore());
            disco.setUserScore(scores.getUserScore());
            disco.setUserRatingText(scores.getUserRatingText());
        }

		return discoRepository.save(disco); 
	}

	@Override
	public List<Disco> obterLista() {
		return discoRepository.findAll();
	}
	
	@Override
	public Disco obterPorID(Integer id) {
		
		if(id == null || id <= 0) {
			throw new IllegalArgumentException("O ID utilizado a busca de Disco não pode ser nulo");
		}
		
		Optional<Disco> disco = discoRepository.findById(id);
		
		return disco.orElseThrow(() -> 
		    new JogoNaoEncontradoException("O disco com o ID ["+id+"] não foi encontrado")
		);
	}

	@Transactional
	@Override
	public Disco alterar(Integer id, Disco disco) {
		
		Disco discoExistente = obterPorID(id);
				
		validarCamposObrigatorios(disco);
		tratarObservacoes(disco);
		
		discoExistente.setTitulo(disco.getTitulo());
        discoExistente.setConsole(disco.getConsole());
        discoExistente.setDesenvolvedora(disco.getDesenvolvedora());
        discoExistente.setGenero(disco.getGenero());
        discoExistente.setAnoLancamento(disco.getAnoLancamento());
        discoExistente.setQuantidadeEmEstoque(disco.getQuantidadeEmEstoque());
        discoExistente.setPrecoCusto(disco.getPrecoCusto());
        discoExistente.setPrecoVenda(disco.getPrecoVenda());
        discoExistente.setObservacoes(disco.getObservacoes());
        
        discoExistente.setDiscoEstado(disco.getDiscoEstadoEnum());
        discoExistente.setDiscoCapa(disco.getDiscoCapaEnum());
        discoExistente.setDiscoConteudoExtra(disco.getDiscoConteudoExtraEnum());
        
        return entityManager.merge(discoExistente);
	
	}


	@Override
	public void excluir(Integer id) {
        
        if(id == null || id <= 0) {
			throw new IllegalArgumentException("O ID para exclusão não pode ser nulo ou zero");
		}
        
        obterPorID(id); 
		discoRepository.deleteById(id);
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
    
    @Transactional 
    public Disco atualizarEstoque(Integer id, Integer quantidadeVendida) {
        
        Disco discoExistente = obterPorID(id); 
        
        int novoEstoque = discoExistente.getQuantidadeEmEstoque() - quantidadeVendida.intValue();

        if (novoEstoque < 0) {
            throw new IllegalArgumentException("Erro de transação: Tentativa de vender mais do que o estoque disponível para Disco ID " + id);
        }
        
        discoExistente.setQuantidadeEmEstoque(novoEstoque);
        
        return entityManager.merge(discoExistente); 
    }


}