package br.com.cabidiomas.student.user.service;

import br.com.cabidiomas.student.user.model.Role;
import br.com.cabidiomas.student.user.model.RolesEnum;
import br.com.cabidiomas.student.user.model.Usuario;
import br.com.cabidiomas.student.user.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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


    public void updateUser(Integer id, Usuario usuario) {
        repository.findById(id).
                map( cliente -> {
                    usuario.setName(usuario.getName());
                    usuario.setLogin(usuario.getLogin());
                    return repository.save(usuario);
                }).
                orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado") );
    }

    public Usuario findById(Integer id) {
        return repository.findById(id).
                orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado") );
    }

    public void delete(Integer id) {
        repository.
                findById(id).
                map( user -> {
                            repository.delete(user);
                            return Void.TYPE;
                        }
                ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

    }
}
