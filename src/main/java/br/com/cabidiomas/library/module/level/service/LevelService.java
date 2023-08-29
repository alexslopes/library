package br.com.cabidiomas.library.module.level.service;

import br.com.cabidiomas.library.module.level.model.Level;
import br.com.cabidiomas.library.module.level.model.StudentLevel;
import br.com.cabidiomas.library.module.level.repository.LevelRepository;
import br.com.cabidiomas.library.module.level.repository.StudentLevelRepository;
import br.com.cabidiomas.library.user.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LevelService {

    private final LevelRepository levelRepository;
    private final UsuarioService usuarioService;
    private final StudentLevelRepository studentLevelRepository;

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

    public void addLevelToStudent(Integer idLevel, Long idUsuario) {
        var level = this.findLevelById(idLevel);
        var user = usuarioService.findById(idUsuario);
        var studentLevel = StudentLevel.builder().usuario(user).level(level).build();
        studentLevelRepository.save(studentLevel);
    }

    public List<Level> getLevelUsuario(Long idUsuario) {
        return studentLevelRepository.findLevelByUsuarioId(idUsuario);
    }
}
