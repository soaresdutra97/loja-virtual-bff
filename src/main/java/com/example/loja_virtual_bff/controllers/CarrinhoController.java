package com.example.loja_virtual_bff.controllers;

import com.example.loja_virtual_bff.api.request.CarrinhoProdutoDTO;
import com.example.loja_virtual_bff.api.request.ProdutoRequestDTO;
import com.example.loja_virtual_bff.api.response.CarrinhoResponseDTO;
import com.example.loja_virtual_bff.business.entities.CarrinhoEntity;
import com.example.loja_virtual_bff.business.services.CarrinhoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrinho")
@Tag(name = "Carrinho de Compras")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @Operation(summary = "Adiciona Produto ao Carrinho", method ="POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Adicionado ao carrinho com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao adicionar ao carrinho"),
    })
    @PostMapping("/adicionar")
    public ResponseEntity<Void> adicionarProdutoAoCarrinho(@RequestParam Long usuarioId, @RequestBody
    CarrinhoProdutoDTO carrinhoProdutoDTO) {
        carrinhoService.adicionarProdutoAoCarrinho(usuarioId, carrinhoProdutoDTO);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Remove Produto do Carrinho por IDs", method ="DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto Removido com Sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao Remover Produto"),
    })
    @DeleteMapping("/remover")
    public ResponseEntity<Void> removerProdutoDoCarrinho(
            @RequestParam Long usuarioId,
            @RequestParam String produtoId) {
        carrinhoService.removerProdutoDoCarrinho(usuarioId, produtoId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Listar Itens do Carrinho por IDs", method ="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos Listados com Sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao Listar Produtos"),
    })
    @GetMapping("/listar")
    public ResponseEntity<List<CarrinhoResponseDTO>> listarItensDoCarrinho(@RequestParam Long usuarioId) {
        List<CarrinhoResponseDTO> itens = carrinhoService.listarItensDoCarrinho(usuarioId);
        return ResponseEntity.ok(itens);
    }
}
