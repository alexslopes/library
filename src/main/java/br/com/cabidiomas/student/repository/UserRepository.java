package br.com.cabidiomas.student.repository;

import br.com.cabidiomas.student.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Usuario, Integer>  {
    Optional<Usuario> findByUsername(String username);

    boolean existsByUsername(String username);
}
