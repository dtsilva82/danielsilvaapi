package br.edu.infnet.danielsilvaapi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import br.edu.infnet.danielsilvaapi.model.domain.Midia;
import br.edu.infnet.danielsilvaapi.model.domain.service.MidiaService;

@Component
public class MidiaLoader implements ApplicationRunner {
	
	private final MidiaService midiaService;
	
	public MidiaLoader(MidiaService midiaService) {
		this.midiaService = midiaService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		FileReader arquivo = new FileReader("midias_catalogo_loja.csv");
		BufferedReader leitura = new BufferedReader(arquivo);
		
		String linha = leitura.readLine();
		String[] campos = null;
		
		while(linha != null) {
			
			campos = linha.split(",");
			
			Midia midia = new Midia();
			midia.setTitulo(campos[0]);
			midia.setConsole(campos[1]);
			midia.setDesenvolvedora(campos[2]);
			midia.setGenero(campos[3]);
			midia.setAnoLancamento(campos[4]);
			midia.setTipoMidia(campos[5]);
			midia.setRegiao(campos[6]);
			midia.setEstadoConservacao(campos[7]);
			midia.setCompletoNaCaixa(Boolean.valueOf(campos[8]));
			midia.setQuantidadeEmEstoque(Integer.valueOf(campos[9]));
			midia.setPrecoCusto(Double.valueOf(campos[10]));
			midia.setPrecoVenda(Double.valueOf(campos[11]));
			midia.setObservacoes(campos[12]);
			
			midiaService.incluir(midia);
			
			linha = leitura.readLine();
		}
		
		Collection<Midia> midias = midiaService.obterLista();
		
		midias.forEach(System.out::println);
		
		leitura.close();

	}
	
	
}


