package com.example.loja_virtual_bff.api.response;

import com.example.loja_virtual_bff.business.entities.UserRole;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String documento;
    private UserRole role;
    private EnderecoResponseDTO endereco;
}