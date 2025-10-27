package br.edu.infnet.danielsilvaapi.model.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import br.edu.infnet.danielsilvaapi.interfaces.CrudService;
import br.edu.infnet.danielsilvaapi.model.domain.Midia;

@Service
public class MidiaService implements CrudService<Midia, Integer> {
	
	private final Map<Integer, Midia> mapa = new ConcurrentHashMap<Integer, Midia>();
	private final AtomicInteger nextId = new AtomicInteger(1);

	@Override
	public Midia incluir(Midia midia) {
		
		midia.setId(nextId.getAndIncrement());
		mapa.put(midia.getId(), midia);
		
		return null;
	}

	@Override
	public List<Midia> obterLista() {
		
		return new ArrayList<Midia>(mapa.values());
	}

	@Override
	public Midia alterar(Integer id, Midia etidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(Integer id) {
		mapa.remove(id);
		
	}

}
