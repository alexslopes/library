package br.com.cabidiomas.library.user.controller;

import br.com.cabidiomas.library.user.controller.dto.UsuarioDto;
import br.com.cabidiomas.library.user.controller.dto.UsuarioMapper;
import br.com.cabidiomas.library.user.model.Role;
import br.com.cabidiomas.library.user.model.Usuario;
import br.com.cabidiomas.library.user.service.RoleService;
import br.com.cabidiomas.library.user.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("{id}")
    public UsuarioDto findById( @PathVariable Long id) {
        var user =  usuarioService.findById(id);
        return UsuarioMapper.entityToDto(user);
    }

    @GetMapping("obter-dados-conta")
    public UsuarioDto getAccountInfo(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        var user = usuarioService.findByLogin(auth.getName());

        return UsuarioMapper.entityToDto(user);
    }

    @PutMapping("atualizar-dados-conta")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAccountInfo(@RequestBody @Valid UsuarioDto usuarioDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        var user = usuarioService.findByLogin(auth.getName());

        if(!Objects.equals(user.getId(), usuarioDto.getId())){
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Acesso não autorizado");
        }

        usuarioService.updateUser(user.getId(), UsuarioMapper.dtoToEntity(usuarioDto, passwordEncoder));
    }

}
