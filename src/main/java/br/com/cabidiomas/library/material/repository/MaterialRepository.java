package br.com.cabidiomas.library.material.repository;

import br.com.cabidiomas.library.material.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository  extends JpaRepository<Material, Long> {

    List<Material> findAllByLevelId(int id);
}
