package com.example.loja_virtual_bff.api.converter;

import com.example.loja_virtual_bff.api.request.EnderecoRequestDTO;
import com.example.loja_virtual_bff.api.request.UsuarioRequestDTO;
import com.example.loja_virtual_bff.api.response.UsuarioResponseDTO;
import com.example.loja_virtual_bff.business.entities.EnderecoEntity;
import com.example.loja_virtual_bff.business.entities.UsuarioEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioEntity converteDTOparaEntity(UsuarioRequestDTO usuarioRequestDTO);
    EnderecoEntity converteDTOparaEntity(EnderecoRequestDTO enderecoRequestDTO);

    UsuarioResponseDTO converteEntityparaDto(UsuarioEntity usuarioEntity);
    List<UsuarioResponseDTO> converteEntityParaDtoList(List<UsuarioEntity> usuarioEntities);

}