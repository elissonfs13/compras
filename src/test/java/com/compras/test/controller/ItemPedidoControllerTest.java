package com.compras.test.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.compras.controller.ItemPedidoController;
import com.compras.model.ItemPedido;
import com.compras.model.Produto;
import com.compras.service.ItemPedidoService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ItemPedidoControllerTest {
	
	@InjectMocks
	private ItemPedidoController itemPedidoController;
	
	@Mock
	private ItemPedidoService itemPedidoService;
	
	private Produto produto;
	private List<ItemPedido> pedidos;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		produto = getProduto();
		pedidos = getItemPedidoList();
	}
	
	@Test
	public void test001AdicionarPedido(){
		itemPedidoController.adicionarProduto(produto);
		verify(itemPedidoService, times(1)).adicionarPedido(produto);
	}
	
	@Test
	public void test002RemoverPedido(){
		itemPedidoController.excluirPedido(pedidos.get(0).getId());
		verify(itemPedidoService, times(1)).removerPedido(pedidos.get(0).getId());
	}
	
	@Test
	public void test003EsvaziarCarrinho(){
		itemPedidoController.editarPedido(pedidos.get(0).getId(), pedidos.get(0));
		verify(itemPedidoService, times(1)).editarPedido(pedidos.get(0).getId(), pedidos.get(0));
	}
	
	private List<ItemPedido> getItemPedidoList() {
		List<ItemPedido> pedidos = new ArrayList<>();
		ItemPedido item = new ItemPedido();
		item.setProduto(produto);
		pedidos.add(item);
		return pedidos;
	}
	
	private Produto getProduto() {
		Produto novoProduto = new Produto();
		novoProduto.setId(99L);
		novoProduto.setImagem("produto99.jpg");
		novoProduto.setNome("Produto 99");
		novoProduto.setValor(10.5d);
		return novoProduto;
	}

}
