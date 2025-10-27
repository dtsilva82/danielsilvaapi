package br.edu.infnet.danielsilvaapi.model.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import br.edu.infnet.danielsilvaapi.exceptions.JogoNaoEncontradoException;
import br.edu.infnet.danielsilvaapi.interfaces.CrudService;
import br.edu.infnet.danielsilvaapi.model.domain.Jogo;

@Service
public class JogoService implements CrudService<Jogo, Integer> {
	
	private final Map<Integer, Jogo> mapa = new ConcurrentHashMap<Integer, Jogo>(); // Mapa armazena Jogo
	private final AtomicInteger nextId = new AtomicInteger(1);
	
	@Override
	public Jogo incluir(Jogo jogo) {
		
		validarCamposObrigatorios(jogo);
		
		tratarObservacoes(jogo);
		
		jogo.setId(nextId.getAndIncrement());
		mapa.put(jogo.getId(), jogo);
		
		return jogo;
	}

	@Override
	public List<Jogo> obterLista() {
		
		return new ArrayList<Jogo>(mapa.values());
	}
	
	@Override
	public Jogo obterPorID(Integer id) {
		
		if(id == null || id <= 0) {
			throw new IllegalArgumentException("O ID utilizado a busca de Jogo não pode ser nulo");
		}
		
		Jogo jogo = mapa.get(id);
		
		if(jogo == null) {
			throw new JogoNaoEncontradoException("O jogo com o ID ["+id+"] não foi encontrado");
		}
		
		return jogo;
	}

	@Override
	public Jogo alterar(Integer id, Jogo jogo) {
		
		validarCamposObrigatorios(jogo);
		tratarObservacoes(jogo);
		jogo.setId(id);
		mapa.put(id, jogo);
		
		return null;
	}


	@Override
	public void excluir(Integer id) {
		Jogo jogo = obterPorID(id);
		mapa.remove(jogo.getId());
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
