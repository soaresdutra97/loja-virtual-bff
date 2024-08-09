package com.example.loja_virtual_bff.business.services;

import com.example.loja_virtual_bff.api.response.ProductsDTO;
import com.example.loja_virtual_bff.infrastructure.clients.produtosclient.ProdutosClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutosService {

    private final ProdutosClient client;

    public List<ProductsDTO> buscaTodosProdutos(){
        return client.buscaTodosProdutos();
    }

    public ProductsDTO buscaProdutosPorNome(String nome){
        return client.buscaProdutosPorNome(nome);
    }

}