package br.com.cabidiomas.student.repository;

import br.com.cabidiomas.student.model.Role;
import br.com.cabidiomas.student.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>  {
    Optional<Usuario> findByLogin(String username);

    boolean existsByLogin(String username);

//    List<Usuario> findAllByRoles(Role role);

    Page<Usuario> findAllByRoles( PageRequest pageRequest, Role role);
}
