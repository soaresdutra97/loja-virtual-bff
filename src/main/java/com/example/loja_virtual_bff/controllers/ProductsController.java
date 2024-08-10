package com.example.loja_virtual_bff.controllers;

import com.example.loja_virtual_bff.api.request.ProdutoRequestDTO;
import com.example.loja_virtual_bff.api.response.ProductsDTO;
import com.example.loja_virtual_bff.api.response.ProdutoResponseDTO;
import com.example.loja_virtual_bff.business.services.ProdutosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/produtos")
@RequiredArgsConstructor
@Tag(name = "API Produtos")
public class ProductsController {

    private final ProdutosService produtosService;

    @Operation(summary = "Busca todos os produtos", method ="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar os dados"),
    })
    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoResponseDTO>> buscaTodosProdutos(){
        return ResponseEntity.ok(produtosService.buscaTodosProdutos());
    }

    @Operation(summary = "Busca produto por ID", method ="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar os dados"),
    })
    @GetMapping("/produtos/search")
    public ResponseEntity <ProdutoResponseDTO> buscaProdutosPorId(@RequestParam("id") String id){
        return ResponseEntity.ok(produtosService.buscaProdutosPorId(id));
    }

    @Operation(summary = "Salva Novo Produto (Admin)", method ="POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto salvo com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao salvar produto"),
    })
    @PostMapping("/")
    public ResponseEntity <ProdutoResponseDTO> salvaNovoProduto(@RequestBody ProdutoRequestDTO produtoRequestDTO){
        return ResponseEntity.ok(produtosService.salvaNovoProduto(produtoRequestDTO));
    }

    @Operation(summary = "Atualiza Produto por ID (Admin)", method ="PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar produto"),
    })
    @PutMapping("/")
    public ResponseEntity <ProdutoResponseDTO> atualizaProdutoPorId(@RequestParam ("id") String id, ProdutoRequestDTO produtoRequestDTO){
        return ResponseEntity.ok(produtosService.atualizaProdutoPorId(id, produtoRequestDTO));
    }

    @Operation(summary = "Deleta Produto por ID (Admin)", method ="DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto deletadocom sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao deletar produto"),
    })
    @DeleteMapping("/")
    public ResponseEntity <Void> deletaProdutoPorId(@RequestParam ("id") String id){
        produtosService.deletaProdutoPorId(id);
        return ResponseEntity.accepted().build();
    }

    @Operation(summary = "Produto existe Boolean", method ="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto deletadocom sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao deletar produto"),
    })
    @GetMapping("/exists")
    public ResponseEntity<Boolean> existsPorId(@RequestParam ("id") String id){
        return ResponseEntity.ok(produtosService.existsPorId(id));
    }
}
