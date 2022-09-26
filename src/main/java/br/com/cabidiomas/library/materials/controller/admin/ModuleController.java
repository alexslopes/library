package br.com.cabidiomas.library.materials.controller.admin;

import br.com.cabidiomas.library.materials.model.Language;
import br.com.cabidiomas.library.materials.model.Module;
import br.com.cabidiomas.library.materials.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/module")
@Secured("ROLE_ADMIN")
@RequiredArgsConstructor
public class ModuleController {

    private final ModuleService moduleService;

    @PostMapping
    public Module save(Module module){
        return moduleService.save(module);
    }
}
