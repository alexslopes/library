package br.com.cabidiomas.student.service;

import br.com.cabidiomas.student.controller.dto.StudentDto;
import br.com.cabidiomas.student.model.Role;
import br.com.cabidiomas.student.model.RolesEnum;
import br.com.cabidiomas.student.model.Usuario;
import br.com.cabidiomas.student.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

//    @Autowired
    private final UsuarioRepository repository;

    public Usuario save(Usuario usuario) throws Exception {
        boolean exists = repository.existsByLogin(usuario.getLogin());
        if(exists){
            throw new Exception("Usuário já cadastrado para o login " + usuario.getLogin());
        }
        return repository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario user = repository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Login nao encontrado"));
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getName())
                .password(user.getPassword())
                .roles(user.getRoles().toString())
                .build();
    }

    public Page<Usuario> findAllStudents(Integer page, Integer pageSize, String sortColumn) {
        Role role = Role.builder().id(RolesEnum.STUDENT.getId()).name(RolesEnum.STUDENT.name()).build();
        Sort sort =  Sort.by(Sort.Direction.ASC, sortColumn);
        PageRequest pageRequest = PageRequest.of(page, pageSize, sort);

        return repository.findAllByRoles(pageRequest, role);


    }


}
