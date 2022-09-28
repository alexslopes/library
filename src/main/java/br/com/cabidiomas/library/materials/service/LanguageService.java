package br.com.cabidiomas.library.materials.service;

import br.com.cabidiomas.library.materials.model.Language;
import br.com.cabidiomas.library.materials.model.Module;
import br.com.cabidiomas.library.materials.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LanguageService {

    private final LanguageRepository languageRepository;

    public Language save(Language module) {
        return languageRepository.save(module);
    }

    public Language findByIdentification(String identification) {
        return languageRepository.findByIdentification(identification).orElseThrow(() -> new UsernameNotFoundException("Idioma nao encontrado"));
    }

    public Language findById(Integer id) {
        return languageRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Idioma nao encontrado"));
    }

    public void delete(Integer id) {
        var language = this.findById(id);
        languageRepository.delete(language);
    }

    public void updateLanguage(Integer id, Language language) {
        var la = this.findById(id);
        la.setDescription(language.getDescription());
        la.setIdentification(language.getIdentification());
        this.save(la);
    }
}
