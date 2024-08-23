package com.example.loja_virtual_bff.controllers;

import com.example.loja_virtual_bff.api.request.UsuarioRequestDTO;
import com.example.loja_virtual_bff.api.response.UsuarioResponseDTO;
import com.example.loja_virtual_bff.business.entities.UsuarioEntity;
import com.example.loja_virtual_bff.business.services.UsuarioService;
import com.example.loja_virtual_bff.infrastructure.security.TokenService;
import feign.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuários")
public class UsuariosController {

    private final TokenService tokenService;

    private final UsuarioService usuariosService;

    @Operation(summary = "Deleta usuário por email (Admin)", method ="DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao deletar dados"),
    })
    @DeleteMapping("/deletar")
    public ResponseEntity <Void> deletaUsuarioPorEmail(@RequestParam ("email") String email){
        usuariosService.deletarUsuarioPorEmail(email);
        return ResponseEntity.accepted().build();
    }

    @Operation(summary = "Atualiza usuário por ID", method ="PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar dados"),
    })
    @PutMapping("/atualizar")
    public ResponseEntity <UsuarioResponseDTO> atualizaUsuarioPorId(@RequestParam ("id") Long id,
                                                                    @RequestBody UsuarioRequestDTO usuarioRequestDTO){
        return ResponseEntity.ok(usuariosService.atualizarUsuarioPorId(usuarioRequestDTO, id));
    }

    @Operation(summary = "Busca usuário por Email (Admin)", method ="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao encontrar usuário"),
    })
    @GetMapping("/buscaremail")
    public ResponseEntity <UsuarioResponseDTO> buscaUsuarioPorEmail(@RequestParam ("email") String email){
        return ResponseEntity.ok(usuariosService.buscarUsuarioPorEmail(email));
    }

    @Operation(summary = "Busca todos os usuários (Admin)", method ="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários encontrados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Usuários não encontrados com sucesso"),
    })
    @GetMapping("/all")
    public ResponseEntity <List<UsuarioResponseDTO>> buscaTodosUsuarios(){
        return ResponseEntity.ok(usuariosService.buscaTodosUsuariosCadastrados());
    }

    @Operation(summary = "Retorna Dados do Usuário pelo Token", method ="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários encontrados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Usuários não encontrados com sucesso"),
    })
    @GetMapping("/usr")
    public ResponseEntity<UsuarioEntity> retornaUsuarioToken(@AuthenticationPrincipal UsuarioEntity usuario){
        return ResponseEntity.ok(usuario);
    }

}