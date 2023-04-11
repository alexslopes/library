package br.com.cabidiomas.library.module.level.controller;

import br.com.cabidiomas.library.module.language.service.LanguageService;
import br.com.cabidiomas.library.module.level.service.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/obter-modulo-por-idioma/{id}")
    public Page<LevelDto> getLevels(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sort", defaultValue = "description") String sort,
            @PathVariable Integer id
    ){
        var levelPage =  levelService.findAllLevelsByLanguage(page, pageSize, sort, id);

        List<LevelDto> levelDtos = levelPage.get().map(LevelMapper::entityToDto).collect(Collectors.toList());

        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC,sort));

        return new PageImpl<>(
                levelDtos,
                pageRequest, levelPage.getTotalElements());
    }
}