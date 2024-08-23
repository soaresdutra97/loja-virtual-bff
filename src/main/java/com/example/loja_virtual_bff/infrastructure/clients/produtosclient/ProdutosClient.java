package com.example.loja_virtual_bff.infrastructure.clients.produtosclient;

import com.example.loja_virtual_bff.api.request.ProdutoRequestDTO;
import com.example.loja_virtual_bff.api.response.ProdutoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(value = "produtos-api", url = "${client2.produtos-client}")
public interface ProdutosClient {


    @GetMapping("/produtos/")
    List<ProdutoResponseDTO> buscaTodosProdutos();

    @GetMapping("/produtos/search")
    ProdutoResponseDTO buscaProdutosPorId(@RequestParam ("id") String nome);

    @PostMapping("/produtos/")
    ProdutoResponseDTO salvaNovosProdutos(@RequestBody ProdutoRequestDTO produtoRequestDTO);

    @PutMapping("/produtos/")
    ProdutoResponseDTO atualizaProdutoPorId(@RequestParam ("id") String id, @RequestBody ProdutoRequestDTO produtoRequestDTO);

    @DeleteMapping("/produtos/")
    void deletaProdutoPorId(@RequestParam ("id") String id);

    @GetMapping("/produtos/exists")
    boolean existsPorId(@RequestParam ("id") String id);

}