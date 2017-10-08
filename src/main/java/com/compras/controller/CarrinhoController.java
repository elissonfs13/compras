package com.compras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.compras.model.Carrinho;
import com.compras.service.CarrinhoService;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {
	
	@Autowired
	private CarrinhoService carrinhoService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Carrinho> buscarCarrinho(){
		Carrinho carrinhoBuscado = carrinhoService.buscarCarrinho();
		return new ResponseEntity<>(carrinhoBuscado, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Carrinho> alterarCarrinho(@RequestBody Carrinho carrinho) {
		Carrinho carrinhoAlterado = carrinhoService.alterar(carrinho);
		return new ResponseEntity<>(carrinhoAlterado, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}" )
	public ResponseEntity<Carrinho> excluirPedido(@PathVariable Long id) {
		carrinhoService.excluir(id);
		return new ResponseEntity<>( HttpStatus.OK);
	}

}
