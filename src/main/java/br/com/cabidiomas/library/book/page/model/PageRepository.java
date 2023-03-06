package br.com.cabidiomas.library.book.page.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PageRepository extends JpaRepository<PageBook, Long> {
    Optional<PageBook> findPageBookById(Long id);
}
