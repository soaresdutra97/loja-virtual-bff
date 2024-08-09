package com.example.loja_virtual_bff.controllers;

import com.example.loja_virtual_bff.api.request.CompraRequestDTO;
import com.example.loja_virtual_bff.api.request.UsuarioRequestDTO;
import com.example.loja_virtual_bff.api.response.ProductsDTO;
import com.example.loja_virtual_bff.api.response.UsuarioResponseDTO;
import com.example.loja_virtual_bff.business.entities.UsuarioEntity;
import com.example.loja_virtual_bff.business.services.LojaVirtualService;
import com.example.loja_virtual_bff.business.services.ProdutosService;
import com.example.loja_virtual_bff.business.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/loja-virtual")
@RequiredArgsConstructor
@Tag(name = "loja-virtual-api")
public class LojaVirtualController {

    private final ProdutosService produtosService;
    private final UsuarioService usuariosService;
    private final LojaVirtualService lojaVirtualService;

    //PRODUTOS

    @Operation(summary = "Busca todos os produtos", method ="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar os dados"),
    })
    @GetMapping("/produtos")
    public ResponseEntity <List<ProductsDTO>> buscaTodosProdutos(){
        return ResponseEntity.ok(produtosService.buscaTodosProdutos());
    }

    @Operation(summary = "Busca produtos por nome", method ="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar os dados"),
    })
    @GetMapping("/produtos/{nome}")
    public ResponseEntity <ProductsDTO> buscaProdutosPorNome(@PathVariable ("nome") String nome){
        return ResponseEntity.ok(produtosService.buscaProdutosPorNome(nome));
    }

    //USUÁRIOS

    @Operation(summary = "Deleta usuário por email (Admin)", method ="DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao deletar dados"),
    })
    @DeleteMapping("/usuario/deletar")
    public ResponseEntity <Void> deletaUsuarioPorEmail(@RequestParam ("email") String email){
        usuariosService.deletarUsuarioPorEmail(email);
        return ResponseEntity.accepted().build();
    }

    @Operation(summary = "Atualiza usuário por ID", method ="PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar dados"),
    })
    @PutMapping("/usuario/atualizar")
    public ResponseEntity <UsuarioResponseDTO> atualizaUsuarioPorId(@RequestParam ("id") Long id, @RequestBody UsuarioRequestDTO usuarioRequestDTO){
        return ResponseEntity.ok(usuariosService.atualizarUsuarioPorId(usuarioRequestDTO, id));
    }

    @Operation(summary = "Busca usuário por Email (Admin)", method ="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao encontrar usuário"),
    })
    @GetMapping("/usuario/buscaremail")
    public ResponseEntity <UsuarioResponseDTO> buscaUsuarioPorEmail(@RequestParam ("email") String email){
        return ResponseEntity.ok(usuariosService.buscarUsuarioPorEmail(email));
    }

    @Operation(summary = "Busca todos os usuários (Admin)", method ="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários encontrados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Usuários não encontrados com sucesso"),
    })
    @GetMapping("/usuario/all")
    public ResponseEntity <List<UsuarioResponseDTO>> buscaTodosUsuarios(){
        return ResponseEntity.ok(usuariosService.buscaTodosUsuariosCadastrados());
    }


    //COMPRA PRODUTOS

    @Operation(summary = "Realizar compra de produto", method ="POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Compra realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar compra"),
    })
    @PostMapping("/compra")
    public ResponseEntity <String> compraProdutos(@RequestBody CompraRequestDTO compraRequestDTO){
        return ResponseEntity.ok(lojaVirtualService.realizaCompraProdutos(compraRequestDTO));
    }

}