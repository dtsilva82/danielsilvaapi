package br.edu.infnet.danielsilvaapi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collection;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import br.edu.infnet.danielsilvaapi.model.domain.DiscoCapa;
import br.edu.infnet.danielsilvaapi.model.domain.DiscoConteudoExtra;
import br.edu.infnet.danielsilvaapi.model.domain.DiscoEstado;
import br.edu.infnet.danielsilvaapi.model.domain.Jogo;
import br.edu.infnet.danielsilvaapi.model.domain.Disco;
import br.edu.infnet.danielsilvaapi.model.domain.service.JogoService;

@Component
public class DiscoLoader implements ApplicationRunner {
	
	private final JogoService jogoService;
	
	public DiscoLoader(JogoService jogoService) {
		this.jogoService = jogoService;
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {

		FileReader arquivo = new FileReader("midias_catalogo_loja.csv");
		BufferedReader leitura = new BufferedReader(arquivo);
		
		String linha = leitura.readLine();
		String[] campos = null;
		
		while(linha != null) {
			
			campos = linha.split(",");
			
			Disco midia = new Disco();
			midia.setTitulo(campos[0]);
			midia.setConsole(campos[1]);
			midia.setDesenvolvedora(campos[2]);
			midia.setGenero(campos[3]);
			midia.setAnoLancamento(campos[4]);
			midia.setDiscoEstado(DiscoEstado.valueOf(campos[5]));
			midia.setDiscoCapa(DiscoCapa.valueOf(campos[6]));
			midia.setDiscoConteudoExtra(DiscoConteudoExtra.valueOf(campos[7]));
			midia.setQuantidadeEmEstoque(Integer.valueOf(campos[8]));
			midia.setPrecoCusto(Double.valueOf(campos[9]));
			midia.setPrecoVenda(Double.valueOf(campos[10]));
			midia.setObservacoes(campos[11]);

				
			jogoService.incluir(midia);
			
			linha = leitura.readLine();
		}
		
		Collection<Jogo> midias = jogoService.obterLista();
		
		midias.forEach(System.out::println);
		
		leitura.close();

	}
	
	
}


