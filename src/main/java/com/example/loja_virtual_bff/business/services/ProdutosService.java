package com.example.loja_virtual_bff.business.services;

import com.example.loja_virtual_bff.api.request.ProdutoRequestDTO;
import com.example.loja_virtual_bff.api.response.ProductsDTO;
import com.example.loja_virtual_bff.api.response.ProdutoResponseDTO;
import com.example.loja_virtual_bff.infrastructure.clients.produtosclient.ProdutosClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutosService {

    private final ProdutosClient client;

    public List<ProdutoResponseDTO> buscaTodosProdutos(){
        return client.buscaTodosProdutos();
    }

    public ProdutoResponseDTO buscaProdutosPorId(String id){
        return client.buscaProdutosPorId(id);
    }

    public ProdutoResponseDTO salvaNovoProduto(ProdutoRequestDTO produtoRequestDTO){
        return client.salvaNovosProdutos(produtoRequestDTO);
    }

    public ProdutoResponseDTO atualizaProdutoPorId(String id, ProdutoRequestDTO produtoRequestDTO){
        return client.atualizaProdutoPorId(id, produtoRequestDTO);
    }

    public void deletaProdutoPorId(String id){
        client.deletaProdutoPorId(id);
    }

    public boolean existsPorId(String id) {
        return client.existsPorId(id);
    }

}