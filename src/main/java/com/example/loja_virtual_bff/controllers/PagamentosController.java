package com.example.loja_virtual_bff.controllers;

import com.example.loja_virtual_bff.api.request.CompraRequestDTO;
import com.example.loja_virtual_bff.business.services.LojaVirtualService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
@Tag(name = "API Pagamentos")
public class PagamentosController {

    private final LojaVirtualService lojaVirtualService;

    @Operation(summary = "Tentativa de Pagamento do Produto", method ="POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tentativa realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar tentativa"),
    })
    @PostMapping("/compra")
    public ResponseEntity<String> compraProdutos(@RequestBody CompraRequestDTO compraRequestDTO){
        return ResponseEntity.ok(lojaVirtualService.realizaCompraProdutos(compraRequestDTO));
    }
}