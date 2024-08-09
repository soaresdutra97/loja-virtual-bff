package com.example.loja_virtual_bff.infrastructure.clients.produtosclient;

import com.example.loja_virtual_bff.api.request.UsuarioRequestDTO;
import com.example.loja_virtual_bff.api.response.ProductsDTO;
import com.example.loja_virtual_bff.api.response.UsuarioResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(value = "produtos-api", url = "localhost:8181")
public interface ProdutosClient {


    @GetMapping("/produtos/")
    List<ProductsDTO> buscaTodosProdutos();

    @GetMapping("/produtos/{nome}")
    ProductsDTO buscaProdutosPorNome(@PathVariable ("nome") String nome);

}