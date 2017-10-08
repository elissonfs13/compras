package com.compras.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.compras.model.Carrinho;
import com.compras.model.ItemPedido;
import com.compras.model.Produto;
import com.compras.repository.CarrinhoRepository;

@Service
public class CarrinhoService {
	
	@Autowired
	private CarrinhoRepository carrinhoRepository;
	
	@Autowired
	private ItemPedidoService itemPedidoService;
	
	public Carrinho buscarCarrinho() {
		List<Carrinho> carrinhos = new ArrayList<>();
		carrinhos = carrinhoRepository.findAll();
		Carrinho carrinho;
		if (CollectionUtils.isEmpty(carrinhos)) {
			carrinho = cadastrar(new Carrinho());
		} else {
			carrinho = carrinhos.get(0);
		}
		calcularValores(carrinho);
		return carrinho;
	}
	
	public void excluir(Long id) {
		Carrinho carrinho = buscarCarrinho();
		itemPedidoService.esvaziarCarrinho(carrinho.getItemPedido());
	}
	
	public Carrinho alterar(Carrinho carrinho) {
		return carrinhoRepository.saveAndFlush(carrinho);
	}
	
	public Carrinho adicionarAoCarrinho(ItemPedido pedido) {
		Carrinho carrinho = buscarCarrinho();
		if (carrinho.getItemPedido() == null) {
			carrinho.setItemPedido(new ArrayList<ItemPedido>());
		}
		ItemPedido itemEncontrado = produtoExistente(carrinho, pedido.getProduto());
		if (itemEncontrado != null) {
			itemEncontrado.setQuantidade(itemEncontrado.getQuantidade() + 1);
			itemPedidoService.editarPedido(itemEncontrado.getId(), itemEncontrado);
		} else {
			pedido.setCarrinho(carrinho);
			itemPedidoService.salvarItemPedido(pedido);
		}
		return alterar(carrinho);
	}	

	private Carrinho cadastrar(Carrinho carrinho) {
		return carrinhoRepository.save(carrinho);
	}
	
	private ItemPedido produtoExistente(Carrinho carrinho, Produto produto) {
		for (ItemPedido itemPedido : carrinho.getItemPedido()) {
			if (itemPedido.getProduto().equals(produto)) {
				return itemPedido;
			}
		}
		return null;
	}
	
	private void calcularValores(Carrinho carrinho) {
		Double valorCarrinho = new Double(0d);
		Double valorItem = new Double(0d);
		if (carrinho.getItemPedido() != null) {
			for (ItemPedido itemPedido : carrinho.getItemPedido()) {
				valorItem = itemPedido.getQuantidade() * itemPedido.getProduto().getValor();
				itemPedido.setPrecoItem(valorItem);
				valorCarrinho = valorCarrinho + valorItem;
			}
		}
		carrinho.setPrecoTotal(valorCarrinho);
	}

}
