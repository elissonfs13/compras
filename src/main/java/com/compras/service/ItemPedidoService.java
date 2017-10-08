package com.compras.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compras.model.Carrinho;
import com.compras.model.ItemPedido;
import com.compras.model.Produto;
import com.compras.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private CarrinhoService carrinhoService;

	public Carrinho adicionarPedido(Produto produto) {
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setProduto(produto);
		itemPedido.setQuantidade(1);
		itemPedido.setPrecoItem(produto.getValor());
		return carrinhoService.adicionarAoCarrinho(itemPedido);
	}
	
	public void removerPedido(Long id) {
		ItemPedido pedido = itemPedidoRepository.getOne(id);
		if (pedido != null) {
			deleteItemPedido(id);
		}
	}
	
	public void esvaziarCarrinho(List<ItemPedido> pedidos) {
		for (ItemPedido pedido : pedidos) {
			deleteItemPedido(pedido.getId());
		}
	}
	
	public void salvarItemPedido(ItemPedido pedido) {
		itemPedidoRepository.saveAndFlush(pedido);
	}
	
	public void editarPedido(Long id, ItemPedido pedido) {
		ItemPedido pedidoDB = itemPedidoRepository.getOne(id);
		if (pedido != null) {
			pedidoDB.setQuantidade(pedido.getQuantidade());
			salvarItemPedido(pedidoDB);
		}
	}
	
	private void deleteItemPedido(Long id) {
		itemPedidoRepository.delete(id);
		itemPedidoRepository.flush();
	}

}