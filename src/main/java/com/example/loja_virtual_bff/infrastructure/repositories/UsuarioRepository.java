package com.example.loja_virtual_bff.infrastructure.repositories;

import com.example.loja_virtual_bff.business.entities.UsuarioEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    UserDetails findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);

}
