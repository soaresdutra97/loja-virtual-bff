package com.example.loja_virtual_bff.api.converter;

import com.example.loja_virtual_bff.api.request.CartaoRequestDTO;
import com.example.loja_virtual_bff.api.request.CompraRequestDTO;
import com.example.loja_virtual_bff.api.request.EnderecoRequestDTO;
import com.example.loja_virtual_bff.api.response.EnderecoResponseDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class LojaVirtualConverter {

    public CartaoRequestDTO paraCartaoRequestDTO(String nome, CompraRequestDTO compraRequestDTO, BigDecimal valor,
                                                 EnderecoResponseDTO enderecoResponseDTO){
        return CartaoRequestDTO.builder()
                .nome(nome)
                .enderecoRequestDTO(paraEnderecoRequestDTO(enderecoResponseDTO))
                .ano(compraRequestDTO.getAno())
                .cvv(compraRequestDTO.getAno())
                .mes(compraRequestDTO.getMes())
                .numeroCartao(compraRequestDTO.getNumeroCartao())
                .valor(valor)
                .build();
    }

    public EnderecoRequestDTO paraEnderecoRequestDTO(EnderecoResponseDTO enderecoResponseDTO){
        return EnderecoRequestDTO.builder()
                .bairro(enderecoResponseDTO.getBairro())
                .cep(enderecoResponseDTO.getCep())
                .cidade(enderecoResponseDTO.getCidade())
                .complemento(enderecoResponseDTO.getComplemento())
                .numero(enderecoResponseDTO.getNumero())
                .rua(enderecoResponseDTO.getRua())
                .build();
    }

}
