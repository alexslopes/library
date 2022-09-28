package br.com.cabidiomas.library.materials.controller.admin.module;

import br.com.cabidiomas.library.materials.model.Module;
import br.com.cabidiomas.library.materials.service.LanguageService;
import br.com.cabidiomas.library.materials.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/module")
@Secured("ROLE_ADMIN")
@RequiredArgsConstructor
public class ModuleController {

    private final ModuleService moduleService;
    private final LanguageService languageService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ModuleDto save(@RequestBody ModuleDto moduleDto){
        var language = languageService.findByIdentification(moduleDto.getLanguageIdentification());
        var module = ModuleMapper.dtoToEntity(moduleDto, language);
        var dto = moduleService.save(module);
        return ModuleMapper.entityToDto(dto);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateModule( @PathVariable Integer id, @RequestBody ModuleDto dto){
        var language = languageService.findByIdentification(dto.getLanguageIdentification());
        var module = ModuleMapper.dtoToEntity(dto, language);
        moduleService.updateModule(id, module);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        moduleService.delete(id);
    }

    @GetMapping("obter-modulo-por-idioma/{id}")
    public List<ModuleDto> getAllModulesByLanguage(@PathVariable Integer id){
        var modules = moduleService.findModuleByLanguage(id);
        return modules.stream().map(ModuleMapper::entityToDto).collect(Collectors.toList());
    }
}
