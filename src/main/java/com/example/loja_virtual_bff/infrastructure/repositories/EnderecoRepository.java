package com.example.loja_virtual_bff.infrastructure.repositories;

import com.example.loja_virtual_bff.business.entities.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {
}
