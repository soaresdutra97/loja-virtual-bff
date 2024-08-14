package com.example.loja_virtual_bff.controllers;

import com.example.loja_virtual_bff.api.request.WishListRequestDTO;
import com.example.loja_virtual_bff.api.response.WishListResponseDTO;
import com.example.loja_virtual_bff.business.services.WishListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
@Tag(name = "Wishlist")
public class WishListController {

    @Autowired
    private WishListService wishListService;


    @Operation(summary = "Adiciona Produto na Wishlist", method ="POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Adicionado na Wishlist com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao adicionar na WishList"),
    })
    @PostMapping("/adicionar")
    public ResponseEntity<Void> adicionarProdutoWishList(@RequestParam Long usuarioId, @RequestBody WishListRequestDTO wishListRequestDTO){
        wishListService.adicionaProdutoWishList(usuarioId, wishListRequestDTO);
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "Remove Produto da WishList por IDs", method ="DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto Removido com Sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao Remover Produto"),
    })
    @DeleteMapping("/remover")
    public ResponseEntity<Void> removerProdutoWishList(@RequestParam Long usuarioId, @RequestParam String produtoId){
        wishListService.removerProdutoWishList(usuarioId, produtoId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Lista Produto da Wishlist por IDs", method ="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos listados com Sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao listar Produto"),
    })
    @GetMapping("/listar")
    public ResponseEntity<List<WishListResponseDTO>> listarItensWishList(@RequestParam Long usuarioId){
        List<WishListResponseDTO> itens = wishListService.listarItensWishList(usuarioId);
        return ResponseEntity.ok(itens);
    }
}