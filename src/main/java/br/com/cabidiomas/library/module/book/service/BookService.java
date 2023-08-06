package br.com.cabidiomas.library.module.book.service;

import br.com.cabidiomas.library.module.level.service.LevelService;
import br.com.cabidiomas.library.module.book.controller.BookDto;
import br.com.cabidiomas.library.module.book.controller.BookMapper;
import br.com.cabidiomas.library.module.book.model.Book;
import br.com.cabidiomas.library.module.book.model.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final LevelService levelService;

    public Book save(BookDto bookDto) {
        var level = levelService.findLevelById(bookDto.getLevelId());
        var page = BookMapper.dtoToEntity(bookDto);
        page.setLevel(level);
        return this.bookRepository.save(page);
    }

    public BookDto findBookById(Long id) {
        var pageBook = this.bookRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Página não encontrada"));
        return BookMapper.entityToDto(pageBook);
    }

    public List<BookDto> findBookByLevel(Integer idLevel) {
        var pageBook = bookRepository.findBookByLevelId(idLevel);
        List<BookDto> listBook = new ArrayList<>();
        pageBook.forEach(book -> listBook.add(BookMapper.entityToDto(book)));
        return listBook;
    }

    public void update(BookDto bookDto) {
        var level = levelService.findLevelById(bookDto.getLevelId());
        var pageBook = BookMapper.dtoToEntity(bookDto, level);
        boolean isExiste = bookRepository.existsById(pageBook.getId());
        if(isExiste){
            this.bookRepository.save(pageBook);
        }
    }

    public void delete(Long id) {
        this.bookRepository.deleteById(id);
    }

}
