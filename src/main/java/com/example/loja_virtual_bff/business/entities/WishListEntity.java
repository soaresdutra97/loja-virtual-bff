package com.example.loja_virtual_bff.business.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "wishlist_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WishListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    @Column(name = "quantidade")
    private String produtoId;
}