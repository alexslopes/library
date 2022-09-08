package br.com.cabidiomas.student.service;

import br.com.cabidiomas.student.model.Usuario;
import br.com.cabidiomas.student.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

//    @Autowired
    private final UsuarioRepository repository;

    public Usuario save(Usuario usuario) throws Exception {
        boolean exists = repository.existsByUsername(usuario.getUsername());
        if(exists){
            throw new Exception("Usuário já cadastrado para o login " + usuario.getUsername());
        }
        return repository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Login nao encontrado"));
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}
