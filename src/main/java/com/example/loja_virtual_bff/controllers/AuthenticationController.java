package com.example.loja_virtual_bff.controllers;

import com.example.loja_virtual_bff.api.request.AuthenticationDTO;
import com.example.loja_virtual_bff.api.request.RegisterRequestDTO;
import com.example.loja_virtual_bff.api.response.LoginResponseDTO;
import com.example.loja_virtual_bff.business.entities.UsuarioEntity;
import com.example.loja_virtual_bff.infrastructure.repositories.UsuarioRepository;
import com.example.loja_virtual_bff.infrastructure.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static com.example.loja_virtual_bff.business.entities.UserRole.USER;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticação")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UsuarioRepository repository;
    @Autowired
    TokenService tokenService;

    @Operation(summary = "Login com um usuário", method ="POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar login"),
    })
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usenamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usenamePassword);
        var token = tokenService.generateToken((UsuarioEntity) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @Operation(summary = "Cria conta usuário", method ="POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao criar usuário"),
    })
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterRequestDTO data){
        if(this.repository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UsuarioEntity newUser = new UsuarioEntity(
                data.email(),
                encryptedPassword,
                data.documento(),
                data.nome(),
                data.endereco());
        newUser.setRole(USER);
        this.repository.save(newUser);
        return ResponseEntity.ok().build();
    }
}