package br.com.cabidiomas.library.book.controller;

import br.com.cabidiomas.library.level.model.Level;
import br.com.cabidiomas.library.level.service.LevelService;
import br.com.cabidiomas.library.book.model.Book;
import br.com.cabidiomas.library.book.service.BookService;
import br.com.cabidiomas.library.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin/book")
@Secured("ROLE_ADMIN")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final LevelService levelService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void save(@RequestParam("idLevel") Integer idLevel,
                     @RequestParam("id") Long id,
                     @RequestParam("description") String description) {
        Level level = levelService.findLevelById(idLevel);
        var book = Book.builder().id(id).
                description(description).
                level(level).build();
        bookService.save(book);
    }

    @GetMapping("{id}")
    public List<Book> list(@PathVariable Integer id){
        return bookService.findAllByLevelId(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        bookService.delete(id);
    }

}
