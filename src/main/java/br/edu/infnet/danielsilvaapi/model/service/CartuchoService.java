package br.edu.infnet.danielsilvaapi.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.infnet.danielsilvaapi.exceptions.JogoNaoEncontradoException;
import br.edu.infnet.danielsilvaapi.interfaces.CrudService;
import br.edu.infnet.danielsilvaapi.model.domain.Cartucho;
import br.edu.infnet.danielsilvaapi.model.domain.Jogo;
import br.edu.infnet.danielsilvaapi.model.repository.CartuchoRepository;

@Service
public class CartuchoService implements CrudService<Cartucho, Integer> { 
	
	private final CartuchoRepository cartuchoRepository;
	
	public CartuchoService(CartuchoRepository cartuchoRepository) {
		this.cartuchoRepository = cartuchoRepository;
	}
	
		
	@Override
	public Cartucho incluir(Cartucho cartucho) {
		
		validarCamposObrigatorios(cartucho);
		tratarObservacoes(cartucho);

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

	@Override
	public Cartucho alterar(Integer id, Cartucho cartucho) {
		
		validarCamposObrigatorios(cartucho);
		tratarObservacoes(cartucho);
		
		cartucho.setId(id);
		
		return cartuchoRepository.save(cartucho);
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