package br.com.cabidiomas.library.module.book.controller;

import br.com.cabidiomas.library.module.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("{id}")
    public BookDto findBookById(@PathVariable Long id) {
        return this.bookService.findBookById(id);
    }

    @GetMapping("/list-by-level/{idLevel}")
    public List<BookDto> getByChapter(
            @PathVariable Integer idLevel
    ){
        return bookService.findBookByLevel(idLevel);
    }

}
