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
import br.edu.infnet.danielsilvaapi.model.service.DiscoService;
import br.edu.infnet.danielsilvaapi.model.domain.Disco;

@Component
public class DiscoLoader implements ApplicationRunner {
	
	private final DiscoService discoService;
	
	public DiscoLoader(DiscoService discoService) {
		this.discoService = discoService;

	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {

		FileReader arquivo = new FileReader("discos_catalogo_loja.csv");
		BufferedReader leitura = new BufferedReader(arquivo);
		
		String linha = leitura.readLine();
		String[] campos = null;
		
		while(linha != null) {
			
			campos = linha.split(",");
			
			Disco disco = new Disco();
			disco.setTitulo(campos[0]);
			disco.setConsole(campos[1]);
			disco.setDesenvolvedora(campos[2]);
			disco.setGenero(campos[3]);
			disco.setAnoLancamento(campos[4]);
			disco.setDiscoEstado(DiscoEstado.valueOf(campos[5]));
			disco.setDiscoCapa(DiscoCapa.valueOf(campos[6]));
			disco.setDiscoConteudoExtra(DiscoConteudoExtra.valueOf(campos[7]));
			disco.setQuantidadeEmEstoque(Integer.valueOf(campos[8]));
			disco.setPrecoCusto(Double.valueOf(campos[9]));
			disco.setPrecoVenda(Double.valueOf(campos[10]));
			disco.setObservacoes(campos[11]);

			discoService.incluir(disco);
			
			linha = leitura.readLine();
		}
		
		
		leitura.close();

	}
	
	
}


