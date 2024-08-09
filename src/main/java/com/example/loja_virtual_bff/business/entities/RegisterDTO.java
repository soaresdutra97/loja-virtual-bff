package com.example.loja_virtual_bff.business.entities;

public record RegisterDTO (
        String nome,
        String email,
        String password,
        UserRole role,
        String documento,
        EnderecoEntity endereco){
}
