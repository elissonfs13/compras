package com.compras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compras.model.ItemPedido;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido,Long> {

}
