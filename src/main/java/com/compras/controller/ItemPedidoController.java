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
import com.compras.model.ItemPedido;
import com.compras.model.Produto;
import com.compras.service.ItemPedidoService;

@RestController
@RequestMapping("/pedido")
public class ItemPedidoController {
	
	@Autowired
	private ItemPedidoService itemPedidoService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Carrinho> adicionarProduto(@RequestBody Produto produto){
		Carrinho carrinhoAtualizado = itemPedidoService.adicionarPedido(produto);
		return new ResponseEntity<>(carrinhoAtualizado, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}" )
	public ResponseEntity<Carrinho> excluirPedido(@PathVariable Long id) {
		itemPedidoService.removerPedido(id);
		return new ResponseEntity<>( HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}" )
	public ResponseEntity<Carrinho> editarPedido(@PathVariable Long id, @RequestBody ItemPedido pedido) {
		itemPedidoService.editarPedido(id, pedido);
		return new ResponseEntity<>( HttpStatus.OK);
	}

}
