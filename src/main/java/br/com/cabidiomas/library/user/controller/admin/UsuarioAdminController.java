package br.com.cabidiomas.library.user.controller.admin;

import br.com.cabidiomas.library.user.controller.dto.UsuarioDto;
import br.com.cabidiomas.library.user.controller.dto.UsuarioMapper;
import br.com.cabidiomas.library.user.service.UsuarioService;
import lombok.RequiredArgsConstructor;
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

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/usuarios")
@Secured("ROLE_ADMIN")
@RequiredArgsConstructor
public class UsuarioAdminController {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDto save(@RequestBody @Valid UsuarioDto usuario) {
        var userEntity = UsuarioMapper.dtoToEntity(usuario, passwordEncoder);
        try {
            var user = usuarioService.save(userEntity);

            return UsuarioMapper.entityToDto(user);
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

        List<UsuarioDto> usuarioDtos = userPage.get().map(UsuarioMapper::entityToDto).collect(Collectors.toList());

        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC,sort));

        return new PageImpl<>(
                usuarioDtos,
                pageRequest, userPage.getTotalElements());
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStudent( @PathVariable Long id, @RequestBody @Valid UsuarioDto usuario) {
        usuarioService.updateUser(id, UsuarioMapper.dtoToEntity(usuario, passwordEncoder));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        usuarioService.delete(id);
    }


}
