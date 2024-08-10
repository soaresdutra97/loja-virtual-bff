package com.example.loja_virtual_bff.business.entities;

import com.example.loja_virtual_bff.api.request.EnderecoRequestDTO;
import com.example.loja_virtual_bff.api.response.ProdutoResponseDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioEntity implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "role", nullable = false)
    private UserRole role;
    @Column(name = "documento", nullable = false, unique = true)
    private String documento;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private EnderecoEntity endereco;


    public UsuarioEntity(String email, String password, UserRole role, String documento, String nome, EnderecoEntity endereco){
        this.email = email;
        this.password = password;
        this.role = role;
        this.documento = documento;
        this.nome = nome;
        this.endereco = endereco;
    }

    public UsuarioEntity(String email, String encryptedPassword, String documento, String nome, EnderecoEntity endereco) {
        this.email = email;
        this.documento = documento;
        this.nome = nome;
        this.endereco = endereco;
        this.password = encryptedPassword;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
