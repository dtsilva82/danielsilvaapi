package br.edu.infnet.danielsilvaapi;

import java.io.BufferedReader;
import java.io.FileReader;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import br.edu.infnet.danielsilvaapi.model.domain.Cartucho; 
import br.edu.infnet.danielsilvaapi.model.domain.CartuchoConservacao;
import br.edu.infnet.danielsilvaapi.model.domain.service.JogoService;


@Component
public class CartuchoLoader implements ApplicationRunner {
	
	private final JogoService jogoService;

	public CartuchoLoader(JogoService jogoService) {
		this.jogoService = jogoService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {


		FileReader arquivo = new FileReader("cartuchos_catalogo_loja.csv"); 
		BufferedReader leitura = new BufferedReader(arquivo);
		
		String linha = leitura.readLine();
		String[] campos = null;
		
		while(linha != null) {
			campos = linha.split(",");

			Cartucho cartucho = new Cartucho(); 
            
			cartucho.setTitulo(campos[0]);
			cartucho.setConsole(campos[1]);
			cartucho.setDesenvolvedora(campos[2]);
			cartucho.setGenero(campos[3]);
			cartucho.setAnoLancamento(campos[4]);
			cartucho.setCartuchoConservacao(CartuchoConservacao.valueOf(campos[5]));
			cartucho.setRegiao(campos[6]);
			cartucho.setPossuiCaixaOriginal(Boolean.valueOf(campos[7]));
			cartucho.setQuantidadeEmEstoque(Integer.valueOf(campos[8]));
			cartucho.setPrecoCusto(Double.valueOf(campos[9]));
			cartucho.setPrecoVenda(Double.valueOf(campos[10]));
			cartucho.setObservacoes(campos[11]);
			 
			jogoService.incluir(cartucho); 
			
			linha = leitura.readLine();
		}
		
		leitura.close();
        
	}
}