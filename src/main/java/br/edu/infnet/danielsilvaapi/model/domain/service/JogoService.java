package br.edu.infnet.danielsilvaapi.model.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import br.edu.infnet.danielsilvaapi.interfaces.CrudService;
import br.edu.infnet.danielsilvaapi.model.domain.Jogo;

@Service
public class JogoService implements CrudService<Jogo, Integer> {
	
	private final Map<Integer, Jogo> mapa = new ConcurrentHashMap<Integer, Jogo>(); // Mapa armazena Jogo
	private final AtomicInteger nextId = new AtomicInteger(1);
	
	@Override
	public Jogo incluir(Jogo jogo) {
		jogo.setId(nextId.getAndIncrement());
		mapa.put(jogo.getId(), jogo);
		
		return jogo;
	}

	@Override
	public List<Jogo> obterLista() {
		
		return new ArrayList<Jogo>(mapa.values());
	}

	@Override
	public Jogo alterar(Integer id, Jogo etidade) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void excluir(Integer id) {
		mapa.remove(id);
	}
}
