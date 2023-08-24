package br.com.cabidiomas.library.module.level.repository;

import br.com.cabidiomas.library.module.level.model.StudentLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentLevelRepository extends JpaRepository<StudentLevel, Long> {

}
