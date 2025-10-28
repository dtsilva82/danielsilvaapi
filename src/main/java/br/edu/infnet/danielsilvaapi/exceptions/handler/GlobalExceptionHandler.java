package br.edu.infnet.danielsilvaapi.exceptions.handler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.edu.infnet.danielsilvaapi.exceptions.JogoNaoEncontradoException;

import org.springframework.validation.FieldError;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> hanldeMethodArgumentNotValidException(MethodArgumentNotValidException e){
		
		Map<String, String> mapa = new HashMap<String, String>();
		
		e.getBindingResult().getAllErrors().forEach((erro) -> {
			String nomeCampo = ((FieldError) erro).getField();
			String mensagemErro = erro.getDefaultMessage();	
			mapa.put(nomeCampo, mensagemErro);
		});
		
		return new ResponseEntity<Map<String,String>>(mapa, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(JogoNaoEncontradoException.class)
	public ResponseEntity<Map<String, String>> hanldeJogoNaoEncontradoException(JogoNaoEncontradoException e){
		
		Map<String, String> mapa = new HashMap<String, String>();
		
		mapa.put("timestamp", LocalDateTime.now().toString());
		mapa.put("status", HttpStatus.NOT_FOUND.toString());
		mapa.put("error", e.getMessage());
		mapa.put("detail", "O ID fornecido não foi encontrado.");
		
		return new ResponseEntity<Map<String,String>>(mapa, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String, String>> hanldeIllegalArgumentException(IllegalArgumentException e){
		
		Map<String, String> mapa = new HashMap<String, String>();
		
		mapa.put("timestamp", LocalDateTime.now().toString());
		mapa.put("status", HttpStatus.BAD_REQUEST.toString());
		mapa.put("error", e.getMessage());
		mapa.put("detail", "Argumento inválido fornecido para esta operação.");
		
		return new ResponseEntity<Map<String,String>>(mapa, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Map<String, String>> hanldeDataIntegrityViolationException(DataIntegrityViolationException e){
		
		Map<String, String> mapa = new HashMap<String, String>();
		
		mapa.put("timestamp", LocalDateTime.now().toString());
		mapa.put("status", HttpStatus.CONFLICT.toString());
		mapa.put("error", e.getMessage());
		mapa.put("detail", "ERRO: Criando um item duplicado.");
		
		return new ResponseEntity<Map<String,String>>(mapa, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Map<String, String>> hanldeRuntimeException(RuntimeException e){
		
		Map<String, String> mapa = new HashMap<String, String>();
		
		mapa.put("timestamp", LocalDateTime.now().toString());
		mapa.put("status", HttpStatus.INTERNAL_SERVER_ERROR.toString());
		mapa.put("error", e.getMessage());
		mapa.put("detail", "Erro interno no servidor.");
		
		return new ResponseEntity<Map<String,String>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}