package br.com.cabidiomas.library.user.controller.dto;

import br.com.cabidiomas.library.user.model.Role;
import br.com.cabidiomas.library.user.model.RolesEnum;
import br.com.cabidiomas.library.user.model.Usuario;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    public static Usuario dtoToEntity(UsuarioDto dto, PasswordEncoder passwordEncoder) {
        if(dto == null) {
            return null;
        }

        if(dto.getPassword() != null) {
            return Usuario.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .login(dto.getLogin())
                    .password(passwordEncoder.encode(dto.getPassword()))
                    .roles(Arrays.stream(dto.getRolesId()).map(Role::getRolebyId).collect(Collectors.toList())).build();
        } else {
            return Usuario.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .login(dto.getLogin())
                    .roles(Arrays.stream(dto.getRolesId()).map(Role::getRolebyId).collect(Collectors.toList())).build();
        }
    }

    public static UsuarioDto entityToDto(Usuario entity) {
        if(entity == null) {
            return null;
        }

        return UsuarioDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .login(entity.getLogin())
                .rolesId(entity.getRoles().stream().map(Role::getId).toArray(Integer[]::new)).build();
    }
}
