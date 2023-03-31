package br.com.cabidiomas.library.module.language.repository;

import br.com.cabidiomas.library.module.language.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
}
