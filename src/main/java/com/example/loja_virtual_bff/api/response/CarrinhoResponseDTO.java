package com.example.loja_virtual_bff.api.response;

import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class CarrinhoResponseDTO {

    private String produtoId;
    private String nome;
    private BigDecimal preco;
    private int quantidade;
    private BigDecimal subtotal;


    public CarrinhoResponseDTO(String produtoId, String nome, BigDecimal preco, int quantidade) {
        this.produtoId = produtoId;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.subtotal = preco.multiply(BigDecimal.valueOf(quantidade));
    }
}
