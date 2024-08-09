package com.example.loja_virtual_bff.api.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRequestDTO {

    private String nome;
    private String email;
    private String documento;
    private EnderecoRequestDTO endereco;
}
