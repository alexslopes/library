package br.com.cabidiomas.library.user.repository;

import br.com.cabidiomas.library.user.model.Role;
import br.com.cabidiomas.library.user.model.RolesEnum;
import br.com.cabidiomas.library.user.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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
            createUser("Admin", "admin", passwordEncoder.encode("123456"), RolesEnum.ROLE_ADMIN);
            createUser("Cliente", "cliente", passwordEncoder.encode("123456"), RolesEnum.ROLE_STUDENT);
        }

    }

    public void createUser(String name, String email, String password, RolesEnum rolesEnum) {

        Role role = roleRepository.findByName(rolesEnum.name());

        Usuario user = Usuario.builder().name(name).login(email).password(password).roles(List.of(role)).build();
        userRepository.save(user);
    }
}
