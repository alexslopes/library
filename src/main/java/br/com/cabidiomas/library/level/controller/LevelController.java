package br.com.cabidiomas.library.level.controller;

import br.com.cabidiomas.library.level.service.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/level")
@RequiredArgsConstructor
public class LevelController {

    private final LevelService levelService;

    @GetMapping("{id}")
    public LevelDto findLevelById(@PathVariable Integer id){
        return LevelMapper.entityToDto(levelService.findLevelById(id));
    }
}
