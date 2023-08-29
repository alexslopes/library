package br.com.cabidiomas.library.module.level.controller;


import br.com.cabidiomas.library.module.level.model.Level;
import br.com.cabidiomas.library.module.level.model.StudentLevel;
import br.com.cabidiomas.library.module.level.service.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/level")
@RequiredArgsConstructor
public class LevelController {

    private final LevelService levelService;

    @GetMapping("{id}")
    public LevelDto findLevelById(@PathVariable Integer id){
         var level = levelService.findLevelById(id);
         return LevelMapper.entityToDto(level);
    }

    @GetMapping("/list-all-level-by-language/{id}")
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

    @GetMapping("obter-modulos-do-usuario/{idUsuario}")
    public List<LevelDto> getLevelForStudent(
            @PathVariable Long idUsuario
    ) {
        return levelService.getLevelUsuario(idUsuario).stream().map(LevelMapper::entityToDto).collect(Collectors.toList());
    }

}
