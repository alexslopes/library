package br.com.cabidiomas.student.user.controller;

import br.com.cabidiomas.student.user.controller.dto.UsuarioDto;
import br.com.cabidiomas.student.user.controller.dto.UsuarioMapper;
import br.com.cabidiomas.student.user.model.Usuario;
import br.com.cabidiomas.student.user.service.RoleService;
import br.com.cabidiomas.student.user.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final RoleService roleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Secured("ROLE_ADMIN")
    public UsuarioDto save(@RequestBody @Valid UsuarioDto usuario) {
        var userEntity = UsuarioMapper.dtoToEntity(usuario);
        try {
            var user = usuarioService.save(userEntity);

            return UsuarioMapper.entityToDto(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/obter-alunos")
    @Secured("ROLE_ADMIN")
    public Page<UsuarioDto> getStudents(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sort", defaultValue = "name") String sort
    ){
        var userPage =  usuarioService.findAllStudents(page, pageSize, sort);

        List<UsuarioDto> usuarioDtos = userPage.get().map(UsuarioMapper::entityToDto).collect(Collectors.toList());

        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC,sort));

        return new PageImpl<>(
                usuarioDtos,
                pageRequest, userPage.getTotalElements());
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStudent( @PathVariable Long id, @RequestBody @Valid UsuarioDto usuario) {
        usuarioService.updateUser(id, UsuarioMapper.dtoToEntity(usuario));
    }

    @GetMapping("{id}")
    public Usuario findById( @PathVariable Long id) {
        return usuarioService.findById(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        usuarioService.delete(id);
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

        usuarioDto.setRoleId(user.getRoles().get(0).getId());

        usuarioService.updateUser(user.getId(), UsuarioMapper.dtoToEntity(usuarioDto));
    }

}
