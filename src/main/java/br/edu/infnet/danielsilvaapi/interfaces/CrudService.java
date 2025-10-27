package br.edu.infnet.danielsilvaapi.interfaces;

import java.util.List;

public interface CrudService<T, ID> {
	
	T incluir(T entidade);
	List<T> obterLista();
	T alterar(ID id, T etidade);
	void excluir(ID id);

}
