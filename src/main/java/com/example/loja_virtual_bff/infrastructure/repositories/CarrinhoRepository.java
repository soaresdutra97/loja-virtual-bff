package com.example.loja_virtual_bff.infrastructure.repositories;

import com.example.loja_virtual_bff.business.entities.CarrinhoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarrinhoRepository extends JpaRepository<CarrinhoEntity, Long> {

    List<CarrinhoEntity> findByUsuarioId(Long usuarioId);
    Optional<CarrinhoEntity> findByUsuarioIdAndProdutoId(Long usuarioId, String produtoId);
}