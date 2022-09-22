package br.com.cabidiomas.library.user.repository;

import br.com.cabidiomas.library.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
