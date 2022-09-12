package br.com.cabidiomas.student.repository;

import br.com.cabidiomas.student.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);
}
