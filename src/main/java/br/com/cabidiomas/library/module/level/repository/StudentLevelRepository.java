package br.com.cabidiomas.library.module.level.repository;

import br.com.cabidiomas.library.module.level.model.Level;
import br.com.cabidiomas.library.module.level.model.StudentLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentLevelRepository extends JpaRepository<StudentLevel, Long> {

    @Query("Select sl.level from StudentLevel sl WHERE sl.usuario.id = ?1 AND sl.level.language.id = ?2")
    List<Level> findLevelByUsuarioId(Long idUsuario, Integer idIdioma);
}
