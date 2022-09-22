package br.com.cabidiomas.library.user.repository;

import br.com.cabidiomas.library.user.model.Role;
import br.com.cabidiomas.library.user.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>  {
    Optional<Usuario> findByLogin(String username);

    boolean existsByLogin(String username);

//    List<Usuario> findAllByRoles(Role role);

    Page<Usuario> findAllByRoles( PageRequest pageRequest, Role role);
}
