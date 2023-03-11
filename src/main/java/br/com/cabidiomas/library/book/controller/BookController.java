package br.com.cabidiomas.library.book.controller;

import br.com.cabidiomas.library.book.page.service.PageBookService;
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
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final PageBookService pageBookService;

    @GetMapping("/list-by-level/{id}")
    public List<BookDetailDto> listByLevelWithDetail(@PathVariable Integer id){
        var books = bookService.findAllByLevelId(id);

        List<BookDetailDto> bookDetailDto = new ArrayList<>();

        for(Book b : books){
            var dto = BookMapper.entityToDto(b, pageBookService.getAllChapterFromBook(b.getId()));
            bookDetailDto.add(dto);
        }

        return bookDetailDto;
    }


}
