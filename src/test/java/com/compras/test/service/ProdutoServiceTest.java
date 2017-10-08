package com.compras.test.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.compras.model.Produto;
import com.compras.repository.ProdutoRepository;
import com.compras.service.ProdutoService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProdutoServiceTest {
	
	@InjectMocks
	private ProdutoService produtoService;
	
	@Mock
	private ProdutoRepository produtoRepository;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test001BuscarTodos(){
		List<Produto> produtos = produtoService.buscarTodos();
		assertNotNull(produtos);
	}


}
