package com.example.loja_virtual_bff.api.request;

import com.example.loja_virtual_bff.business.entities.EnderecoEntity;
import com.example.loja_virtual_bff.business.entities.UserRole;

public record RegisterDTO (
        String nome,
        String email,
        String password,
        UserRole role,
        String documento,
        EnderecoEntity endereco){
}
