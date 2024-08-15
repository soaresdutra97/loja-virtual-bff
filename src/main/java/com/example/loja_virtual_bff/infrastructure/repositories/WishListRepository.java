package com.example.loja_virtual_bff.infrastructure.repositories;

import com.example.loja_virtual_bff.business.entities.WishListEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishListRepository extends JpaRepository<WishListEntity, Long> {

    List<WishListEntity> findByUsuarioId(Long usuarioId);
    Optional<WishListEntity> findByUsuarioIdAndProdutoId(Long usurioId, String produtoId);
    Optional<WishListEntity> findByProdutoId(String id);

    @Transactional
    void deleteByProdutoId(String id);
}
