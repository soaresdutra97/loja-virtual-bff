package com.example.loja_virtual_bff.infrastructure.repositories;

import com.example.loja_virtual_bff.business.entities.CarrinhoEntity;
import com.example.loja_virtual_bff.business.entities.WishListEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarrinhoRepository extends JpaRepository<CarrinhoEntity, Long> {

    List<CarrinhoEntity> findByUsuarioId(Long usuarioId);
    Optional<CarrinhoEntity> findByUsuarioIdAndProdutoId(Long usuarioId, String produtoId);
    Optional<CarrinhoEntity> findByProdutoId(String id);


    @Transactional
    void deleteByProdutoId(String id);
}