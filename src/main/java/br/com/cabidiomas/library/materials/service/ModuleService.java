package br.com.cabidiomas.library.materials.service;

import br.com.cabidiomas.library.materials.model.Module;
import br.com.cabidiomas.library.materials.repository.ModuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModuleService {

    private final ModuleRepository moduleRepository;

    public Module save(Module module) {
        return moduleRepository.save(module);
    }
}
