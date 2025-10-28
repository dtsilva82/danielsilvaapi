package br.edu.infnet.danielsilvaapi.interfaces;

import java.util.List;

import br.edu.infnet.danielsilvaapi.model.domain.Cartucho;
import br.edu.infnet.danielsilvaapi.model.domain.Disco;
import br.edu.infnet.danielsilvaapi.model.domain.Jogo;

public interface CrudService<T, ID> {
	
	T incluir(T entidade);
	List<T> obterLista();
	T alterar(ID id, T etidade);
	void excluir(ID id);
	T obterPorID(ID id);
}
