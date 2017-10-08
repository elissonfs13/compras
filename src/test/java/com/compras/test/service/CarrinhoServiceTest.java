package com.compras.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
import org.mockito.MockitoAnnotations;

import com.compras.model.Carrinho;
import com.compras.model.ItemPedido;
import com.compras.model.Produto;
import com.compras.repository.CarrinhoRepository;
import com.compras.service.CarrinhoService;
import com.compras.service.ItemPedidoService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CarrinhoServiceTest {
	
	@InjectMocks
	private CarrinhoService carrinhoService;
	
	@Mock
	private CarrinhoRepository carrinhoRepository;
	
	@Mock
	private ItemPedidoService itemPedidoService;
	
	private List<Carrinho> carrinhos;
	private Carrinho carrinho;
	private ItemPedido pedido;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		carrinhos = new ArrayList<>();
		carrinho = getCarrinho();
		pedido = getPedido();
	}

	@Test
	public void test001BuscarCarrinho(){
		carrinhos.add(carrinho);
		when(carrinhoRepository.findAll()).thenReturn(carrinhos);
		Carrinho carrinhoReturned = carrinhoService.buscarCarrinho();
		assertNotNull(carrinhoReturned);
		verify(carrinhoRepository, times(1)).findAll();
	}
	
	@Test
	public void test002BuscarCarrinhoVazio(){
		Carrinho novoCarrinho = new Carrinho();
		when(carrinhoRepository.findAll()).thenReturn(carrinhos);
		when(carrinhoRepository.save(novoCarrinho)).thenReturn(novoCarrinho);
		Carrinho carrinhoReturned = carrinhoService.buscarCarrinho();
		assertNotNull(carrinhoReturned);
		verify(carrinhoRepository, times(1)).findAll();
	}
	
	@Test
	public void test003Excluir(){
		carrinhos.add(carrinho);
		when(carrinhoRepository.findAll()).thenReturn(carrinhos);
		carrinhoService.excluir(carrinho.getId());
		verify(itemPedidoService, times(1)).esvaziarCarrinho(carrinho.getItemPedido());
	}
	
	@Test
	public void test004Alterar(){
		carrinhoService.alterar(carrinho);
		verify(carrinhoRepository, times(1)).saveAndFlush(carrinho);
	}
	
	@Test
	public void test005AdicionarAoCarrinhoItemPedidoNaoEncontrado(){
		carrinhos.add(carrinho);
		when(carrinhoRepository.findAll()).thenReturn(carrinhos);
		when(carrinhoRepository.saveAndFlush(carrinho)).thenReturn(carrinho);
		Carrinho carrinhoReturned = carrinhoService.adicionarAoCarrinho(pedido);
		assertNotNull(carrinhoReturned);
		verify(itemPedidoService, times(1)).salvarItemPedido(pedido);
	}
	
	@Test
	public void test006AdicionarAoCarrinhoItemPedidoEncontrado(){
		List<ItemPedido> pedidos = new ArrayList<>();
		pedidos.add(pedido);
		carrinho.setItemPedido(pedidos);
		carrinhos.add(carrinho);
		when(carrinhoRepository.findAll()).thenReturn(carrinhos);
		when(carrinhoRepository.saveAndFlush(carrinho)).thenReturn(carrinho);
		Carrinho carrinhoReturned = carrinhoService.adicionarAoCarrinho(pedido);
		assertNotNull(carrinhoReturned);
		verify(itemPedidoService, times(1)).editarPedido(pedido.getId(), pedido);
	}
	
	@Test
	public void test007CalcularValores(){
		List<ItemPedido> pedidos = new ArrayList<>();
		pedidos.add(pedido);
		carrinho.setItemPedido(pedidos);
		carrinhos.add(carrinho);
		when(carrinhoRepository.findAll()).thenReturn(carrinhos);
		Carrinho carrinhoReturned = carrinhoService.buscarCarrinho();
		assertNotNull(carrinhoReturned);
		assertEquals(new Double(10.5d), carrinhoReturned.getPrecoTotal());
	}

	private Carrinho getCarrinho() {
		Carrinho carrinho = new Carrinho();
		carrinho.setId(99L);
		return carrinho;
	}
	
	private ItemPedido getPedido() {
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setId(99L);
		itemPedido.setQuantidade(1);
		itemPedido.setProduto(getProduto());
		return itemPedido;
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
