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
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final LevelService levelService;

    @GetMapping("/list-by-level/{id}")
    public List<Book> listByLevel(@PathVariable Integer id){
        return bookService.findAllByLevelId(id);
    }

//    @GetMapping("/get-content/{id}")
//    public BookDto getContent(@PathVariable Long id){
//        var book =  bookService.findById(id);
//
//        if(book != null)
//            return BookDto.builder().content(book.getPages()).build();
//
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado");
//    }

}
