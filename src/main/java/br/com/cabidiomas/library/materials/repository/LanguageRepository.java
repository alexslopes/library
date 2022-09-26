package br.com.cabidiomas.library.materials.repository;

import br.com.cabidiomas.library.materials.model.Language;
import br.com.cabidiomas.library.materials.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
    Optional<Language> findByIdentification(String identification);
}
