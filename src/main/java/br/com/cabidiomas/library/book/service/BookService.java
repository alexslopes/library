package br.com.cabidiomas.library.book.service;

import br.com.cabidiomas.library.book.model.Book;
import br.com.cabidiomas.library.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public void save(Book book) {
        bookRepository.save(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> findAllByLevelId(Long id) {
        return bookRepository.findAllByLevelId(id);
    }

    public Book findById(Long id){
        return bookRepository.findById(id).get();
    }
}
