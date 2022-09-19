package br.com.cabidiomas.student.user.controller;

import br.com.cabidiomas.student.user.controller.dto.UsuarioDto;
import br.com.cabidiomas.student.user.controller.dto.UsuarioMapper;
import br.com.cabidiomas.student.user.model.Role;
import br.com.cabidiomas.student.user.model.Usuario;
import br.com.cabidiomas.student.user.service.RoleService;
import br.com.cabidiomas.student.user.service.UsuarioService;
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
    public UsuarioDto save(@RequestBody @Valid UsuarioDto usuario) {
        var userEntity = UsuarioMapper.dtoToEntity(usuario);
        try {
            var user = usuarioService.save(userEntity);

            return UsuarioMapper.dtoToEntity(user);
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

        List<UsuarioDto> usuarioDtos = userPage.get().map(user -> UsuarioMapper.dtoToEntity(user)).collect(Collectors.toList());

        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC,sort));

        return new PageImpl<>(
                usuarioDtos,
                pageRequest, userPage.getSize());
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

}
