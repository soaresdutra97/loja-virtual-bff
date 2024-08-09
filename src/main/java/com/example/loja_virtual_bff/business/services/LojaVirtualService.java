package com.example.loja_virtual_bff.business.services;

import com.example.loja_virtual_bff.api.converter.LojaVirtualConverter;
import com.example.loja_virtual_bff.api.request.CompraRequestDTO;
import com.example.loja_virtual_bff.api.response.ProductsDTO;
import com.example.loja_virtual_bff.api.response.UsuarioResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LojaVirtualService {

    private final ProdutosService produtosService;
    private final PagamentosService pagamentosService;
    private final UsuarioService usuariosService;
    private final LojaVirtualConverter converter;

    public String realizaCompraProdutos(CompraRequestDTO compraRequestDTO){

        UsuarioResponseDTO usuario = buscaDadosUsuario(compraRequestDTO.getEmail());
        ProductsDTO produto = buscaDadosProduto(compraRequestDTO.getProduto());
        Boolean response = verificaPagamento(usuario, compraRequestDTO, produto);

        return response ? "Pagamento aprovado" : "Pagamento Negado";
    }

    private UsuarioResponseDTO buscaDadosUsuario(String email){
        return usuariosService.buscarUsuarioPorEmail(email);
    }


    private ProductsDTO buscaDadosProduto(String nome){
        return produtosService.buscaProdutosPorNome(nome);
    }

    private Boolean verificaPagamento(UsuarioResponseDTO usuario, CompraRequestDTO compraRequestDTO, ProductsDTO produto){
        return pagamentosService.verificaPagamento(converter.paraCartaoRequestDTO(usuario.getNome(),
                compraRequestDTO, produto.getPreco(), usuario.getEndereco()));

    }

}