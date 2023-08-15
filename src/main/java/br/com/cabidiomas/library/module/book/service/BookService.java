package br.com.cabidiomas.library.module.book.service;

import br.com.cabidiomas.library.module.level.service.LevelService;
import br.com.cabidiomas.library.module.book.controller.BookDto;
import br.com.cabidiomas.library.module.book.controller.BookMapper;
import br.com.cabidiomas.library.module.book.model.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final LevelService levelService;

    public BookDto save(BookDto bookDto) {
        var level = levelService.findLevelById(bookDto.getLevelId());
        var page = BookMapper.dtoToEntity(bookDto);
        page.setLevel(level);
        var bookToReturn = this.bookRepository.save(page);
        return BookDto.builder().id(bookToReturn.getId()).
                levelId(bookToReturn.getLevel().getId()).
                content(bookToReturn.getContent()).build();
    }

    public BookDto findBookById(Long id) {
        var pageBook = this.bookRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Página não encontrada"));
        return BookMapper.entityToDto(pageBook);
    }

    public BookDto findBookByLevel(Integer idLevel) {
        var book = bookRepository.findBookByLevelId(idLevel);
        return BookMapper.entityToDto(book);
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
