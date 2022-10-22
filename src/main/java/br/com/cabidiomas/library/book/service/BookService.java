package br.com.cabidiomas.library.book.service;

import br.com.cabidiomas.library.book.model.Book;
import br.com.cabidiomas.library.book.repository.BookRepository;
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
public class BookService {

    private final BookRepository bookRepository;
    private final LanguageService languageService;

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Book findBookById(Integer id){
        return bookRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Módulo não encontrado"));
    }

    public void delete(Integer id) {
        var module = this.findBookById(id);
        bookRepository.delete(module);
    }

    public void updateModule(Integer id, Book book) {
        var moduleToUpdate = this.findBookById(id);
        moduleToUpdate.setLanguage(book.getLanguage());
        moduleToUpdate.setDescription(book.getDescription());
        this.save(moduleToUpdate);
    }

    public Page<Book> findAllBooksByLanguage(Integer page, Integer pageSize, String sortColumn, Integer id) {
        Sort sort =  Sort.by(Sort.Direction.ASC, sortColumn);
        PageRequest pageRequest = PageRequest.of(page, pageSize, sort);

        return bookRepository.findAllByLanguageId(pageRequest, id);
    }
}
