package com.example.loja_virtual_bff.business.services;

import com.example.loja_virtual_bff.api.request.CarrinhoRequestDTO;
import com.example.loja_virtual_bff.api.response.CarrinhoResponseDTO;
import com.example.loja_virtual_bff.api.response.ProdutoResponseDTO;
import com.example.loja_virtual_bff.business.entities.CarrinhoEntity;
import com.example.loja_virtual_bff.business.entities.UsuarioEntity;
import com.example.loja_virtual_bff.business.entities.WishListEntity;
import com.example.loja_virtual_bff.infrastructure.repositories.CarrinhoRepository;
import com.example.loja_virtual_bff.infrastructure.repositories.UsuarioRepository;
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
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutosService produtosService;

    public void adicionarProdutoAoCarrinho(Long usuarioId, CarrinhoRequestDTO carrinhoRequestDTO) {

        // Verifica se o produto existe
        boolean produtoExiste = produtosService.existsPorId(carrinhoRequestDTO.getProdutoId());

        if (!produtoExiste) {
            throw new RuntimeException("Produto não encontrado");
        }

        // Verifica se o usuário existe
        UsuarioEntity usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Verifica se o produto já existe no carrinho
        CarrinhoEntity itemExistente = carrinhoRepository.findByUsuarioIdAndProdutoId(usuarioId, carrinhoRequestDTO.getProdutoId())
                .orElse(null);

        if (itemExistente != null) {
            // Se o produto já existe, atualiza a quantidade
            itemExistente.setQuantidade(itemExistente.getQuantidade() + carrinhoRequestDTO.getQuantidade());
            carrinhoRepository.save(itemExistente);
        } else {
            // Se o produto não existe, cria um novo item no carrinho
            CarrinhoEntity novoItem = new CarrinhoEntity();
            novoItem.setUsuario(usuario);
            novoItem.setProdutoId(carrinhoRequestDTO.getProdutoId());
            novoItem.setQuantidade(carrinhoRequestDTO.getQuantidade());
            carrinhoRepository.save(novoItem);
        }
    }

    public void removerProdutoDoCarrinho(Long usuarioId, String produtoId) {
        // Verifica se o usuário existe
        UsuarioEntity usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Encontra e remove o item do carrinho
        CarrinhoEntity item = carrinhoRepository.findByUsuarioIdAndProdutoId(usuarioId, produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado no carrinho"));
        carrinhoRepository.delete(item);
    }


    //Garante integridade Referencial
    @KafkaListener(topics = "atualizaCarrinhoeWishList", groupId = "carrinho-group")
    public void deletaProdutosCarrinhoPelaAPI(String id){
        CarrinhoEntity itemExiste = carrinhoRepository.findByProdutoId(id).orElse(null);

        if(itemExiste != null){
            carrinhoRepository.deleteByProdutoId(id);
        }
    }



    public List<CarrinhoResponseDTO> listarItensDoCarrinho(Long usuarioId) {
        // Verifica se o usuário existe
        UsuarioEntity usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Lista os itens do carrinho e converte para DTO
        List<CarrinhoEntity> itens = carrinhoRepository.findByUsuarioId(usuarioId);
        return itens.stream().map(item -> {
            // Busca as informações do produto na API de produtos
            ProdutoResponseDTO produto = produtosService.buscaProdutosPorId(item.getProdutoId());

            // Retorna o DTO com as informações do produto
            return new CarrinhoResponseDTO(
                    item.getProdutoId(),
                    produto.getNome(),
                    produto.getPreco(),
                    item.getQuantidade()
            );
        }).collect(Collectors.toList());
    }
}