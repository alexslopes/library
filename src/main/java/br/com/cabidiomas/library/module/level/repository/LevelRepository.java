package br.com.cabidiomas.library.module.level.repository;

import br.com.cabidiomas.library.module.level.model.Level;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level, Integer> {

    Page<Level> findAllByLanguageId(PageRequest pageRequest, Integer id);
}
