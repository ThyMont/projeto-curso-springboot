package com.thymont.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thymont.domain.Produto;
import com.thymont.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository repo;
	
	public Optional<Produto> buscar(Integer id) {
		return repo.findById(id);
	}
}
