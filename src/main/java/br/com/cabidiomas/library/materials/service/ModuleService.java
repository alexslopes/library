package br.com.cabidiomas.library.materials.service;

import br.com.cabidiomas.library.materials.model.Module;
import br.com.cabidiomas.library.materials.repository.ModuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModuleService {

    private final ModuleRepository moduleRepository;
    private final LanguageService languageService;

    public Module save(Module module) {
        return moduleRepository.save(module);
    }

    public Module findModuleById(Integer id){
        return moduleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Módulo não encontrado"));
    }

    public List<Module> findModuleByLanguage(Integer id){
        return moduleRepository.findByLanguageId(id);
    }

    public void delete(Integer id) {
        var module = this.findModuleById(id);
        moduleRepository.delete(module);
    }

    public void updateModule(Integer id, Module module) {
        var moduleToUpdate = this.findModuleById(id);
        moduleToUpdate.setLanguage(module.getLanguage());
        moduleToUpdate.setDescription(module.getDescription());
        this.save(moduleToUpdate);
    }
}
