package br.com.cabidiomas.library.language.controller;

import br.com.cabidiomas.library.language.model.Language;
import br.com.cabidiomas.library.language.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/language")
@Secured("ROLE_ADMIN")
@RequiredArgsConstructor
public class LanguageAdminController {

    private final LanguageService languageService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid LanguageDto languageDto){
        var language = Language.builder().description(languageDto.getDescription()).build();
        languageService.save(language);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLanguage( @PathVariable Integer id, @RequestBody Language language){
        languageService.updateLanguage(id, language);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        languageService.delete(id);
    }

}
