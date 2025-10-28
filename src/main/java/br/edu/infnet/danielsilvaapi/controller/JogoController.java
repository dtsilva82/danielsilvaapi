package br.edu.infnet.danielsilvaapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

import br.edu.infnet.danielsilvaapi.exceptions.JogoNaoEncontradoException;
import br.edu.infnet.danielsilvaapi.model.domain.Jogo;
import br.edu.infnet.danielsilvaapi.model.domain.Cartucho;
import br.edu.infnet.danielsilvaapi.model.domain.Disco;
import br.edu.infnet.danielsilvaapi.model.service.CartuchoService;
import br.edu.infnet.danielsilvaapi.model.service.DiscoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/jogos")
public class JogoController {

	private final DiscoService discoService;
    private final CartuchoService cartuchoService;

	public JogoController(DiscoService discoService, CartuchoService cartuchoService) {
		this.discoService = discoService;
        this.cartuchoService = cartuchoService;
	}

	@PostMapping
	public ResponseEntity<Jogo> incluir(@Valid @RequestBody Jogo jogo) {

		if (jogo instanceof Disco) {
			
			Disco discoIncluido = discoService.incluir((Disco) jogo);
            
			return ResponseEntity.status(HttpStatus.CREATED).body(discoIncluido);
		} else if (jogo instanceof Cartucho) {
    			
        		Cartucho cartuchoIncluido = cartuchoService.incluir((Cartucho) jogo);
                
    			return ResponseEntity.status(HttpStatus.CREATED).body(cartuchoIncluido);
        }

        throw new IllegalArgumentException("Tipo de jogo inválido ou não reconhecido no payload.");
	}

	@GetMapping
    public ResponseEntity<List<Jogo>> obterLista(
        @RequestParam(required = false) String tipo
    ) {
        List<Jogo> listaCompleta = new ArrayList<>();
        listaCompleta.addAll(discoService.obterLista());
        listaCompleta.addAll(cartuchoService.obterLista());

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
	public ResponseEntity<Jogo> obterPorId(@PathVariable Integer id) {
		
		try {
			Disco discoObtido = discoService.obterPorID(id);
            return ResponseEntity.ok(discoObtido);
        }
		catch (JogoNaoEncontradoException e) {
			Cartucho cartuchoObtido = cartuchoService.obterPorID(id);
            return ResponseEntity.ok(cartuchoObtido);
        }
	}

	@PutMapping("/{id}")
	public ResponseEntity<Jogo> alterar(@PathVariable Integer id, @RequestBody Jogo jogo) {

        if (jogo instanceof Disco) {
        	
        		Disco discoAlterado = discoService.alterar(id, (Disco) jogo);
        		
        		return ResponseEntity.ok(discoAlterado);
        		
        } else if (jogo instanceof Cartucho) {
       		
        	Cartucho cartuchoAlterado = cartuchoService.alterar(id, (Cartucho) jogo);
    		
       		return ResponseEntity.ok(cartuchoAlterado);
        }

        throw new IllegalArgumentException("Tipo de jogo inválido ou não reconhecido no payload para alteração.");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        try {
            discoService.excluir(id);

            return ResponseEntity.noContent().build();

        }
        catch (JogoNaoEncontradoException e) {
            try {
                cartuchoService.excluir(id);

                return ResponseEntity.noContent().build();

            }
            catch (JogoNaoEncontradoException e2) {

                throw new JogoNaoEncontradoException("O Jogo com o ID [" + id + "] não foi encontrado em nenhum catálogo para exclusão.");
            }
        }
	}
}