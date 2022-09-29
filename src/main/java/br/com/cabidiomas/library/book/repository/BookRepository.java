package br.com.cabidiomas.library.book.repository;

import br.com.cabidiomas.library.book.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Page<Book> findAllByLanguageId(PageRequest pageRequest, Integer id);
}
