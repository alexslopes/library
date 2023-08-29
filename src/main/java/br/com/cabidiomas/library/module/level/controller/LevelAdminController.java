package br.com.cabidiomas.library.module.level.controller;

import br.com.cabidiomas.library.module.language.service.LanguageService;
import br.com.cabidiomas.library.module.level.service.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/level")
@Secured("ROLE_ADMIN")
@RequiredArgsConstructor
public class LevelAdminController {

    private final LevelService levelService;
    private final LanguageService languageService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LevelDto save(@RequestBody LevelDto levelDto){
        var language = languageService.findById(levelDto.getLanguageId());
        var level = LevelMapper.dtoToEntity(levelDto, language);
        return LevelMapper.entityToDto(levelService.save(level));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLevel( @RequestBody LevelDto levelDto){
        var language = languageService.findById(levelDto.getLanguageId());
        var level = LevelMapper.dtoToEntity(levelDto, language);
        var dto = levelService.save(level);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        levelService.delete(id);
    }

    @PostMapping("salvar-modulo-para-usuario/{idLevel}/{idUsuario}")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveLevelForStudent(
            @PathVariable Integer idLevel,
            @PathVariable Long idUsuario
    ) {
       levelService.addLevelToStudent(idLevel, idUsuario);
    }

    @DeleteMapping("deletar-modulo-para-usuario/{idLevel}/{idUsuario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLevelForStudent(
            @PathVariable Integer idLevel,
            @PathVariable Long idUsuario
    ) {
        levelService.deleteLevelToStudent(idLevel, idUsuario);
    }

}
