package br.com.cabidiomas.student.user.controller.dto;

import br.com.cabidiomas.student.user.model.Role;
import br.com.cabidiomas.student.user.model.RolesEnum;
import br.com.cabidiomas.student.user.model.Usuario;

import java.util.Arrays;
import java.util.List;

public class UsuarioMapper {

    public static Usuario dtoToEntity(UsuarioDto dto) {
        if(dto == null) {
            return null;
        }

        assert RolesEnum.getRoleById(dto.getRoleId()) != null;
        return Usuario.builder()
                .id(dto.getId())
                .name(dto.getName())
                .login(dto.getLogin())
                .password(dto.getPassword())
                .roles(List.of(Role.getRolebyEnum(RolesEnum.getRoleById(dto.getRoleId())))).build();
    }

    public static UsuarioDto entityToDto(Usuario entity) {
        if(entity == null) {
            return null;
        }

        return UsuarioDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .login(entity.getLogin())
                .password(entity.getPassword())
                .roleId(entity.getRoles().get(0).getId()).build();
    }
}
