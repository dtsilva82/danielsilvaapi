package br.edu.infnet.danielsilvaapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.edu.infnet.danielsilvaapi.model.domain.Jogo;
import br.edu.infnet.danielsilvaapi.model.domain.Cartucho;
import br.edu.infnet.danielsilvaapi.model.domain.Disco;
import br.edu.infnet.danielsilvaapi.model.domain.service.JogoService;

@RestController
@RequestMapping("/api/jogos")
public class JogoController {
	
	private final JogoService jogoService;
	
	public JogoController(JogoService jogoService) {
		this.jogoService = jogoService;
	}
	
	@PostMapping
	public Jogo incluir(@RequestBody Jogo jogo) {
		
		Jogo jogoIncluido = jogoService.incluir(jogo);

		return (Jogo) jogoIncluido;
	}
	
	@GetMapping
    public ResponseEntity<List<Jogo>> obterLista(
        @RequestParam(required = false) String tipo
    ) {
        List<Jogo> listaCompleta = jogoService.obterLista();
        
        if (tipo == null || tipo.isEmpty()) {
            return ResponseEntity.ok(listaCompleta);
        }
        
        String tipoFiltro = tipo.toLowerCase();

        if ("cartucho".equals(tipoFiltro) || "disco".equals(tipoFiltro)) {
            
            List<Jogo> listaFiltrada = listaCompleta.stream()
                .filter(jogo -> {
                    if ("cartucho".equals(tipoFiltro)) {
                        return jogo instanceof Cartucho;
                    }
                    if ("disco".equals(tipoFiltro)) {
                        return jogo instanceof Disco;
                    }
                    return false; 
                })
                .collect(Collectors.toList());
                
            return ResponseEntity.ok(listaFiltrada);

        }
        throw new IllegalArgumentException(
                "Valor de filtro 'tipo' não reconhecido. Use 'disco' ou 'cartucho'."
            );
	}
	
	@GetMapping("/{id}")
	public Jogo obterPorId(@PathVariable Integer id) {
		
		Jogo jogoObtido = jogoService.obterPorID(id);
		
		return jogoObtido;
	}
	
	@PutMapping("/{id}")
	public Jogo alterar(@PathVariable Integer id, @RequestBody Jogo jogo) {
		
		Jogo jogoObtido = jogoService.alterar(id, jogo);
		
		return jogoObtido;
	}
	
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Integer id) {
		jogoService.excluir(id);
	}
}
