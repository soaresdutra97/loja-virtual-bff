package com.example.loja_virtual_bff.api.request;

import com.example.loja_virtual_bff.business.entities.EnderecoEntity;
import com.example.loja_virtual_bff.business.entities.UserRole;

public record RegisterRequestDTO (String nome,
                                  String email,
                                  String password,
                                  String documento,
                                  EnderecoEntity endereco){
}
