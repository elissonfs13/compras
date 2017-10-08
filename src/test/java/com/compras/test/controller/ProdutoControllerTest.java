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

import com.compras.controller.ProdutoController;
import com.compras.service.ProdutoService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProdutoControllerTest {
	
	@InjectMocks
	private ProdutoController produtoController;
	
	@Mock
	private ProdutoService produtoService;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test001BuscarProdutos(){
		produtoController.buscarTodosProdutos();
		verify(produtoService, times(1)).buscarTodos();
	}

}
