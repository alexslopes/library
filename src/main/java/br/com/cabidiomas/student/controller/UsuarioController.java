package br.com.cabidiomas.student.controller;

import br.com.cabidiomas.student.controller.dto.UsuarioDto;
import br.com.cabidiomas.student.model.Role;
import br.com.cabidiomas.student.model.Usuario;
import br.com.cabidiomas.student.service.RoleService;
import br.com.cabidiomas.student.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final RoleService roleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid UsuarioDto usuario) {
        Role role = roleService.findRoleById(usuario.getRoleId());
        try {
            usuarioService.save(Usuario.builder()
                    .login(usuario.getLogin())
                    .name(usuario.getName())
                    .password(usuario.getPassword())
                    .roles(Arrays.asList(role)).build());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/obter-alunos")
    public Page<UsuarioDto> getStudents(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sort", defaultValue = "name") String sort
    ){
        var userPage =  usuarioService.findAllStudents(page, pageSize, sort);

        List<UsuarioDto> usuarioDtos = userPage.get().map(user -> UsuarioDto.builder()
                .id(user.getId())
                .name(user.getName())
                .login(user.getLogin()).build()).collect(Collectors.toList());

        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC,sort));

        return new PageImpl<>(
                usuarioDtos,
                pageRequest, userPage.getSize());
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStudent( @PathVariable Integer id, @RequestBody @Valid UsuarioDto usuario) {
        usuarioService.updateUser(id, Usuario.builder()
                .name(usuario.getName())
                .login(usuario.getLogin()).build());
    }

    @GetMapping("{id}")
    public Usuario findById( @PathVariable Integer id) {
        return usuarioService.findById(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        usuarioService.delete(id);
    }

}
