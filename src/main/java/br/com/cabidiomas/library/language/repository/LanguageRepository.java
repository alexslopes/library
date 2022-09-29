package br.com.cabidiomas.library.language.repository;

import br.com.cabidiomas.library.language.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
    Optional<Language> findByIdentification(String identification);
}
