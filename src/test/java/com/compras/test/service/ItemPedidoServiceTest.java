package com.compras.test.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.compras.model.ItemPedido;
import com.compras.model.Produto;
import com.compras.repository.ItemPedidoRepository;
import com.compras.service.CarrinhoService;
import com.compras.service.ItemPedidoService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ItemPedidoServiceTest {
	
	@InjectMocks
	private ItemPedidoService itemPedidoService;
	
	@Mock
	private ItemPedidoRepository itemPedidoRepository;
	
	@Mock
	private CarrinhoService carrinhoService;
	
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
		itemPedidoService.adicionarPedido(produto);
		verify(carrinhoService, times(1)).adicionarAoCarrinho(new ItemPedido());
	}
	
	@Test
	public void test002RemoverPedido(){
		itemPedidoService.removerPedido(pedidos.get(0).getId());
		verify(itemPedidoRepository, times(1)).getOne(pedidos.get(0).getId());
	}
	
	@Test
	public void test003EsvaziarCarrinho(){
		itemPedidoService.esvaziarCarrinho(pedidos);
		verify(itemPedidoRepository, times(1)).delete(pedidos.get(0).getId());
	}
	
	@Test
	public void test004SalvarItemPedido(){
		itemPedidoService.salvarItemPedido(pedidos.get(0));
		verify(itemPedidoRepository, times(1)).saveAndFlush(pedidos.get(0));
	}
	
	@Test
	public void test005EditarPedido(){
		when(itemPedidoRepository.getOne(Mockito.anyLong())).thenReturn(pedidos.get(0));
		itemPedidoService.editarPedido(pedidos.get(0).getId(), pedidos.get(0));
		verify(itemPedidoRepository, times(1)).getOne(pedidos.get(0).getId());
		verify(itemPedidoRepository, times(1)).saveAndFlush(pedidos.get(0));
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
