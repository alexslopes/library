package br.com.cabidiomas.library.material.service;

import br.com.cabidiomas.library.material.model.Material;
import br.com.cabidiomas.library.material.repository.MaterialRepository;
import br.com.cabidiomas.library.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialService {

    private final MaterialRepository materialRepository;

    public void save(Material material) {
        materialRepository.save(material);
    }

    public void delete(Long id) {
        materialRepository.deleteById(id);
    }

    public List<Material> findAllByLevelId(Integer id) {
        return materialRepository.findAllByLevelId(id);
    }
}
