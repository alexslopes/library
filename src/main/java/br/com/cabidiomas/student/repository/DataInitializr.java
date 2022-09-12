package br.com.cabidiomas.student.repository;

import br.com.cabidiomas.student.model.Const;
import br.com.cabidiomas.student.model.Role;
import br.com.cabidiomas.student.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializr implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UsuarioRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {

        List<Usuario> users = userRepository.findAll();

        if (users.isEmpty()) {
            createUser("Admin", "admin", passwordEncoder.encode("123456"), Const.ROLE_ADMIN);
            createUser("Cliente", "cliente", passwordEncoder.encode("123456"), Const.ROLE_CLIENT);
        }

    }

    public void createUser(String name, String email, String password, String roleName) {

        Role role = Role.builder().name(roleName).build();

        this.roleRepository.save(role);
        Usuario user = Usuario.builder().username(name).password(password).roles(Arrays.asList(role)).build();
        userRepository.save(user);
    }
}
