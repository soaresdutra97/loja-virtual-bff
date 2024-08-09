package com.example.loja_virtual_bff.business.services;

import com.example.loja_virtual_bff.api.converter.UsuarioMapper;
import com.example.loja_virtual_bff.api.converter.UsuarioUpdateMapper;
import com.example.loja_virtual_bff.api.request.UsuarioRequestDTO;
import com.example.loja_virtual_bff.api.response.UsuarioResponseDTO;
import com.example.loja_virtual_bff.business.entities.UsuarioEntity;
import com.example.loja_virtual_bff.infrastructure.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.util.Assert.notNull;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final UsuarioUpdateMapper usuarioUpdateMapper;

    public UsuarioEntity cadastrarUsuario(UsuarioEntity usuarioEntity){
        return usuarioRepository.saveAndFlush(usuarioEntity);
    }

    public UsuarioResponseDTO salvaNovoUsuario(UsuarioRequestDTO usuarioRequestDTO){
        notNull(usuarioRequestDTO, "Os dados do usuário são obrigatórios");
        UsuarioEntity usuarioEntity = cadastrarUsuario(usuarioMapper.converteDTOparaEntity(usuarioRequestDTO));
        return usuarioMapper.converteEntityparaDto(usuarioEntity);
    }

    public UsuarioResponseDTO atualizarUsuarioPorId(UsuarioRequestDTO usuarioRequestDTO, Long id){
        try{
            notNull(usuarioRequestDTO, "Os dados do usuário são obrigatórios");
            UsuarioEntity usuarioEntity = usuarioRepository.findById(id).orElseThrow(()-> new RuntimeException("Erro"));
            usuarioUpdateMapper.updateUsuarioFromDTO(usuarioRequestDTO, usuarioEntity);
            return usuarioMapper.converteEntityparaDto(cadastrarUsuario(usuarioEntity));
        } catch (Exception e){
            throw new RuntimeException("Erro");
        }
    }

    public UsuarioResponseDTO buscarUsuarioPorEmail(String email){
        return usuarioMapper.converteEntityparaDto((UsuarioEntity) usuarioRepository.findByEmail(email));
    }

    public void deletarUsuarioPorEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }

    public List<UsuarioResponseDTO> buscaTodosUsuariosCadastrados(){
        return usuarioMapper.converteEntityParaDtoList(usuarioRepository.findAll());
    }
}