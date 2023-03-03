package br.com.cabidiomas.library.book.controller;

import br.com.cabidiomas.library.book.model.Book;
import br.com.cabidiomas.library.book.service.BookService;
import br.com.cabidiomas.library.level.model.Level;
import br.com.cabidiomas.library.level.service.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@Secured("ROLE_ADMIN")
@RequestMapping("/api/admin/book")
@RequiredArgsConstructor
public class BookAdminController {

    private final BookService bookService;
    private final LevelService levelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody BookDto bookDto) {
        Level level = levelService.findLevelById(bookDto.getIdLevel());
        var book = Book.builder().
                description(bookDto.getDescription()).
                level(level).
                content(bookDto.getContent()).build();
        bookService.save(book);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        bookService.delete(id);
    }

}
