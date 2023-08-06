package br.com.cabidiomas.library.module.book.controller;


import br.com.cabidiomas.library.module.book.model.Book;
import br.com.cabidiomas.library.module.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/book")
@Secured("ROLE_ADMIN")
@RequiredArgsConstructor
public class BookAdminController {

    private final BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book save(@RequestBody BookDto bookDto) {
        return bookService.save(bookDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody BookDto pageDto) {
        this.bookService.update(pageDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        bookService.delete(id);
    }
}
