package com.example.loja_virtual_bff.api.response;

import com.example.loja_virtual_bff.business.entities.UsuarioEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishListResponseDTO {

    private String produtoId;
    private String nome;
    private BigDecimal preco;


}
