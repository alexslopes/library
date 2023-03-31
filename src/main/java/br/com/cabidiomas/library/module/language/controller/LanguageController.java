package br.com.cabidiomas.library.module.language.controller;

import br.com.cabidiomas.library.module.language.model.Language;
import br.com.cabidiomas.library.module.language.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/language")
@Secured("ROLE_ADMIN")
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageService languageService;

    @GetMapping("obter-todos-idiomas")
    public List<Language> getAllLanguages(){
        return languageService.getAllLanguage();
    }

}
