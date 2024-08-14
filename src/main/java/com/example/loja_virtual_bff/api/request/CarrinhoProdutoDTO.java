package com.example.loja_virtual_bff.api.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class CarrinhoProdutoDTO {

    private String produtoId;
    private int quantidade;

}
