package br.com.cabidiomas.library.book.page.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PageRepository extends JpaRepository<PageBook, Long> {
    Optional<PageBook> findPageBookById(Long id);

    Page<PageBook> findAllByBookIdAndChapter(PageRequest pageRequest, Long idBook, Integer chapter);
}
