package br.edu.infnet.danielsilvaapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.danielsilvaapi.model.domain.Midia;
import br.edu.infnet.danielsilvaapi.model.domain.service.MidiaService;

@RestController
@RequestMapping("/api/midias")
public class MidiaController {
	
	private final MidiaService midiaService;
	
	public MidiaController(MidiaService midiaService) {
		this.midiaService = midiaService;
	}
	
	// endpoints
	
	@PostMapping
	public Midia incluir(@RequestBody Midia midia) {
		
		Midia midiaIncluida = midiaService.incluir(midia);
		
		return midiaIncluida;
	}
	
	@GetMapping
	public List<Midia> obterLista() {
		
		List<Midia> lista = new ArrayList<Midia>();
		lista.add(new Midia());
		
		return midiaService.obterLista();
	}

}
