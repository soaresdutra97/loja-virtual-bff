package com.example.loja_virtual_bff.business.services;

import com.example.loja_virtual_bff.api.request.WishListRequestDTO;
import com.example.loja_virtual_bff.api.response.ProdutoResponseDTO;
import com.example.loja_virtual_bff.api.response.WishListResponseDTO;
import com.example.loja_virtual_bff.business.entities.UsuarioEntity;
import com.example.loja_virtual_bff.business.entities.WishListEntity;
import com.example.loja_virtual_bff.infrastructure.repositories.UsuarioRepository;
import com.example.loja_virtual_bff.infrastructure.repositories.WishListRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Service
public class WishListService {

    @Autowired
    private WishListRepository wishListRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProdutosService produtosService;

    public void adicionaProdutoWishList(Long usuarioId, WishListRequestDTO wishListRequestDTO){

        //Verifica se o produto existe
        boolean produtoExiste = produtosService.existsPorId(wishListRequestDTO.getProdutoId());

        if(!produtoExiste){
            throw new RuntimeException("Produto não encontrado");
        }

        //Verifica se o usuário existe
        UsuarioEntity usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(()-> new RuntimeException("Usuário não encontrado"));

        //Verifica se o produto já existe na Wishlist
        WishListEntity itemExistente = wishListRepository.findByUsuarioIdAndProdutoId(usuarioId, wishListRequestDTO.getProdutoId())
                .orElse(null);

        if(itemExistente == null){
            WishListEntity novoItem = new WishListEntity();
            novoItem.setUsuario(usuario);
            novoItem.setProdutoId(wishListRequestDTO.getProdutoId());
            wishListRepository.save(novoItem);
        }
    }

    public void removerProdutoWishList(Long usuarioId, String produtoId){
        //Verifica se o usuário existe
        UsuarioEntity usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(()-> new RuntimeException("Usuário não encontrado"));

        //Encontra e remove o item da Wishlist
        WishListEntity item = wishListRepository.findByUsuarioIdAndProdutoId(usuarioId, produtoId)
                .orElseThrow(()-> new RuntimeException("Produto não encontrado no carrinho"));
        wishListRepository.delete(item);
    }


    //Garante integridade Referencial
    @KafkaListener(topics = "atualizaCarrinhoeWishList", groupId = "wishlist-group")
    public void deletaProdutosWishListPelaAPIProdutos(String id){
        WishListEntity itemExiste = wishListRepository.findByProdutoId(id).orElse(null);

        if(itemExiste != null){
            wishListRepository.deleteByProdutoId(id);
        }
    }


    public List<WishListResponseDTO> listarItensWishList(Long usuarioId){
        //Verifica se o usuário existe
        UsuarioEntity usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(()-> new RuntimeException("Usuário não encontrado"));

        //Lista os itens da WishList e converte para DTO
        List<WishListEntity> itens = wishListRepository.findByUsuarioId(usuarioId);
        return itens.stream().map(item ->{
            //Busca as informações do produto na API de produtos
            ProdutoResponseDTO produto = produtosService.buscaProdutosPorId(item.getProdutoId());

            // Retorna o DTO com as informações do produto
            return new WishListResponseDTO(
                    item.getProdutoId(),
                    produto.getNome(),
                    produto.getPreco()
            );
        }).collect(Collectors.toList());
    }
}