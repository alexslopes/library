package br.com.cabidiomas.library.book.service;

import br.com.cabidiomas.library.book.model.Book;
import br.com.cabidiomas.library.book.repository.BookRepository;
import br.com.cabidiomas.library.language.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final LanguageService languageService;

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Book findModuleById(Integer id){
        return bookRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Módulo não encontrado"));
    }

    public List<Book> findModuleByLanguage(Integer id){
        return bookRepository.findByLanguageId(id);
    }

    public void delete(Integer id) {
        var module = this.findModuleById(id);
        bookRepository.delete(module);
    }

    public void updateModule(Integer id, Book book) {
        var moduleToUpdate = this.findModuleById(id);
        moduleToUpdate.setLanguage(book.getLanguage());
        moduleToUpdate.setDescription(book.getDescription());
        this.save(moduleToUpdate);
    }
}
