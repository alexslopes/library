package br.com.cabidiomas.student.user.repository;

import br.com.cabidiomas.student.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
