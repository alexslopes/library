package br.com.cabidiomas.library.materials.controller.admin.language;

import br.com.cabidiomas.library.materials.controller.admin.module.ModuleDto;
import br.com.cabidiomas.library.materials.controller.admin.module.ModuleMapper;
import br.com.cabidiomas.library.materials.model.Language;
import br.com.cabidiomas.library.materials.service.LanguageService;
import br.com.cabidiomas.library.user.controller.dto.UsuarioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin/language")
@Secured("ROLE_ADMIN")
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageService languageService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language save(@RequestBody @Valid Language language){
        return languageService.save(language);
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
