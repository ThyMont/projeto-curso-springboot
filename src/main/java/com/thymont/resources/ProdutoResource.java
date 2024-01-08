package com.thymont.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thymont.domain.Produto;
import com.thymont.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<?> procuraPorId(@PathVariable Integer id) {
		Optional<Produto> categoria = service.buscar(id);
		if (categoria.isPresent()) {
			return ResponseEntity.ok().body(categoria);			
		}
		return ResponseEntity.notFound().build();
	}
}
