package br.com.cabidiomas.library.level.service;

import br.com.cabidiomas.library.level.model.Level;
import br.com.cabidiomas.library.level.repository.LevelRepository;
import br.com.cabidiomas.library.language.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class LevelService {

    private final LevelRepository levelRepository;

    public Level save(Level level) {
        return levelRepository.save(level);
    }

    public Level findLevelById(Integer id){
        return levelRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Módulo não encontrado"));
    }

    public void delete(Integer id) {
        var module = this.findLevelById(id);
        levelRepository.delete(module);
    }

    public void updateModule(Integer id, Level level) {
        var moduleToUpdate = this.findLevelById(id);
        moduleToUpdate.setLanguage(level.getLanguage());
        moduleToUpdate.setDescription(level.getDescription());
        this.save(moduleToUpdate);
    }

    public Page<Level> findAllLevelsByLanguage(Integer page, Integer pageSize, String sortColumn, Integer id) {
        Sort sort =  Sort.by(Sort.Direction.ASC, sortColumn);
        PageRequest pageRequest = PageRequest.of(page, pageSize, sort);

        return levelRepository.findAllByLanguageId(pageRequest, id);
    }
}
