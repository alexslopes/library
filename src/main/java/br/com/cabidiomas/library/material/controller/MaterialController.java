package br.com.cabidiomas.library.material.controller;

import br.com.cabidiomas.library.level.model.Level;
import br.com.cabidiomas.library.level.service.LevelService;
import br.com.cabidiomas.library.level.service.LevelService;
import br.com.cabidiomas.library.material.model.Material;
import br.com.cabidiomas.library.material.service.MaterialService;
import br.com.cabidiomas.library.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin/material")
@Secured("ROLE_ADMIN")
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialService materialService;
    private final LevelService levelService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void save(@RequestParam("idLevel") Integer idLevel,
                     @RequestParam("id") Long id,
                     @RequestParam("description") String description,
                     @RequestParam("file") Part file) throws IOException {
        Level level = levelService.findLevelById(idLevel);
        var material = Material.builder().id(id).
                description(description).
                level(level).
                file(FileUtils.partToBytes(file)).build();
        materialService.save(material);
    }

    @GetMapping("{id}")
    public List<Material> list(@PathVariable Integer id){
        return materialService.findAllByLevelId(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        materialService.delete(id);
    }

}
