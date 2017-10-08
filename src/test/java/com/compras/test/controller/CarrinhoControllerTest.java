package com.compras.test.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.compras.controller.CarrinhoController;
import com.compras.model.Carrinho;
import com.compras.service.CarrinhoService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CarrinhoControllerTest {
	
	@InjectMocks
	private CarrinhoController carrinhoController;
	
	@Mock
	private CarrinhoService carrinhoService;
	
	private Carrinho carrinho;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		carrinho = getCarrinho();
	}
	
	@Test
	public void test001BuscarCarrinho(){
		carrinhoController.buscarCarrinho();
		verify(carrinhoService, times(1)).buscarCarrinho();
	}
	
	@Test
	public void test002ExcluirPedido(){
		carrinhoController.excluirPedido(carrinho.getId());
		verify(carrinhoService, times(1)).excluir(carrinho.getId());
	}
	
	@Test
	public void test003AlterarCarrinho(){
		carrinhoController.alterarCarrinho(carrinho);
		verify(carrinhoService, times(1)).alterar(carrinho);
	}
	
	private Carrinho getCarrinho() {
		Carrinho carrinho = new Carrinho();
		carrinho.setId(99L);
		return carrinho;
	}

}
