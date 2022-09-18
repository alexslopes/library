package br.com.cabidiomas.student.controller;

import br.com.cabidiomas.student.controller.dto.StudentDto;
import br.com.cabidiomas.student.model.Usuario;
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
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid Usuario usuario) {
        try {
            usuarioService.save(usuario);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/obter-alunos")
    public Page<StudentDto> getStudents(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "size", defaultValue = "name") String sort
    ){
        var userPage =  usuarioService.findAllStudents(page, pageSize, sort);

        List<StudentDto> studentDtos = userPage.get().map(user -> StudentDto.builder()
                .id(user.getId())
                .name(user.getName())
                .login(user.getLogin()).build()).collect(Collectors.toList());

        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC,sort));

        return new PageImpl<>(
                studentDtos,
                pageRequest, userPage.getSize());
    }
}
