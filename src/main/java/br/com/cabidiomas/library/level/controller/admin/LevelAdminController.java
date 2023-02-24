package br.com.cabidiomas.library.level.controller.admin;

import br.com.cabidiomas.library.level.controller.LevelDto;
import br.com.cabidiomas.library.level.controller.LevelMapper;
import br.com.cabidiomas.library.language.service.LanguageService;
import br.com.cabidiomas.library.level.service.LevelService;
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
        var module = LevelMapper.dtoToEntity(levelDto, language);
        var dto = levelService.save(module);
        return LevelMapper.entityToDto(dto);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateModule( @PathVariable Integer id, @RequestBody LevelDto dto){
        var language = languageService.findById(dto.getLanguageId());
        var module = LevelMapper.dtoToEntity(dto, language);
        levelService.updateModule(id, module);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        levelService.delete(id);
    }

    @GetMapping("/obter-modulo-por-idioma/{id}")
    public Page<LevelDto> getSLevels(
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
