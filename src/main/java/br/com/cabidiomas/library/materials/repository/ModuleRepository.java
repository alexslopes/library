package br.com.cabidiomas.library.materials.repository;

import br.com.cabidiomas.library.materials.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Integer> {
    List<Module> findByLanguageId(Integer id);
}
