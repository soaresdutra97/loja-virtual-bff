package com.example.loja_virtual_bff.controllers;

import com.example.loja_virtual_bff.business.entities.AuthenticationDTO;
import com.example.loja_virtual_bff.business.entities.LoginResponseDTO;
import com.example.loja_virtual_bff.business.entities.RegisterDTO;
import com.example.loja_virtual_bff.business.entities.UsuarioEntity;
import com.example.loja_virtual_bff.infrastructure.repositories.UsuarioRepository;
import com.example.loja_virtual_bff.infrastructure.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UsuarioRepository repository;
    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usenamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usenamePassword);
        var token = tokenService.generateToken((UsuarioEntity) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.repository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UsuarioEntity newUser = new UsuarioEntity(
                data.email(),
                encryptedPassword,
                data.role(),
                data.documento(),
                data.nome(),
                data.endereco());
        this.repository.save(newUser);
        return ResponseEntity.ok().build();
    }
}