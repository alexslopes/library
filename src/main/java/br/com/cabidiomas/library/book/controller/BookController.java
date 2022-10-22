package br.com.cabidiomas.library.book.controller;

import br.com.cabidiomas.library.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("{id}")
    public BookDto findBookById(@PathVariable Integer id){
        return BookMapper.entityToDto(bookService.findBookById(id));
    }
}
