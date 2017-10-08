package com.compras.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.compras.model.Produto;
import com.compras.repository.ProdutoRepository;

@Service
public class ProdutoService {
		
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<Produto> buscarTodos() {
		List<Produto> produtos = produtoRepository.findAll();
		if (produtos == null || CollectionUtils.isEmpty(produtos)) {
			preCadastroProdutos();
			produtos = produtoRepository.findAll();
		}
		return produtos;
	}
	
	//Itens pré cadastrados no sistema
	private void preCadastroProdutos() {
		cadastrarNovoProduto("Camisa Palmeiras I 17/18 Torcedor Adidas Masculina", 199.9, "produto1.jpg");
		cadastrarNovoProduto("Bola Futebol de Campo Adidas", 59.9, "produto2.jpg");
		cadastrarNovoProduto("Tênis Mizuno Jet N Masculino", 199.9, "produto3.jpg");
		cadastrarNovoProduto("Boné Puma BMW MTS Aba Curva BB ", 109.9, "produto4.jpg");
		cadastrarNovoProduto("Sandália Nike Sunray Adjust 4 Juvenil", 119.8, "produto5.jpg");
		cadastrarNovoProduto("Mochila Speedo Braid Masculina", 59.9, "produto6.jpg");
		cadastrarNovoProduto("Jaqueta Running CLN Divoks com Proteção UV Pink", 119.0, "produto7.jpg");
		cadastrarNovoProduto("Barraca de Camping GONEW 4 pessoas", 99.9, "produto8.jpg");
		cadastrarNovoProduto("Whey Protein 6 Six Protein Refil Bodybuilders 2 Kg", 79.9, "produto9.jpg");
		cadastrarNovoProduto("Óculos de Sol Oakley Sliver PRIZM Polarizado", 389.9, "produto10.jpg");
		cadastrarNovoProduto("Bicicleta Aro 29 Mountain Bike GONEW - 24 marchas", 1499.9, "produto11.jpg");
		cadastrarNovoProduto("Relógio X - Games XKPPD021 BXBX", 58.41, "produto12.jpg");
	}
	
	private void cadastrarNovoProduto(String nome, Double valor, String imagem) {
		Produto novoProduto = new Produto();
		novoProduto.setNome(nome);
		novoProduto.setValor(valor);
		novoProduto.setImagem(imagem);
		
		produtoRepository.saveAndFlush(novoProduto);
	}

}
