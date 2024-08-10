package com.example.loja_virtual_bff.business.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "carrinho_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarrinhoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    @Column(name = "produto_id")
    private String produtoId;

    @Column(name = "quantidade")
    private int quantidade;


    // Construtor com parâmetros
    public CarrinhoEntity(UsuarioEntity usuario, Long produtoId, int quantidade) {
        this.usuario = usuario;
        this.produtoId = String.valueOf(produtoId);
        this.quantidade = quantidade;
    }
}