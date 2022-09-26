package br.com.cabidiomas.library.materials.controller.admin;

import br.com.cabidiomas.library.materials.model.Language;
import br.com.cabidiomas.library.materials.service.LanguageService;
import br.com.cabidiomas.library.user.controller.dto.UsuarioDto;
import lombok.RequiredArgsConstructor;
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
    public Language save(@RequestBody @Valid Language language){
        return languageService.save(language);
    }

    @DeleteMapping("{identification}")
    public void delete(@PathVariable String identification){
        languageService.delete(identification);
    }

}
