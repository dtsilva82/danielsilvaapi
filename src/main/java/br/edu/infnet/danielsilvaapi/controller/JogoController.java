package br.edu.infnet.danielsilvaapi.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.danielsilvaapi.model.domain.Jogo;
import br.edu.infnet.danielsilvaapi.model.domain.Disco;
import br.edu.infnet.danielsilvaapi.model.domain.service.JogoService;

@RestController
@RequestMapping("/api/midias")
public class JogoController {
	
	private final JogoService jogoService;
	
	public JogoController(JogoService jogoService) {
		this.jogoService = jogoService;
	}
	
	
	@PostMapping
	public Jogo incluir(@RequestBody Jogo jogo) {
		
		Jogo jogoIncluido = jogoService.incluir(jogo);
		
		return jogoIncluido;
	}
	
	@GetMapping
	public List<Jogo> obterLista() {
		
		//List<Midia> lista = new ArrayList<Midia>();
		//lista.add(new Midia());
		
		return jogoService.obterLista();
	}

}
