package br.com.cabidiomas.library.book.page.controller;

import br.com.cabidiomas.library.book.model.Book;
import br.com.cabidiomas.library.book.page.model.PageBook;
import br.com.cabidiomas.library.book.page.service.PageBookService;
import br.com.cabidiomas.library.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/page")
@Secured("ROLE_ADMIN")
@RequiredArgsConstructor
public class PageBookAdminController {

    private final PageBookService pageBookService;
    private final BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody PageBookDto pageDto) {
        Book book = bookService.findById(pageDto.getBookId());
        var page = PageBook.builder()
                .pageIndex(pageDto.getPageIndex())
                .book(book)
                .chapter(pageDto.getChapter())
                .content(pageDto.getContent()).build();

        this.pageBookService.save(page);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody PageBookDto pageDto) {
        var page = PageBook.builder()
                        .pageIndex(pageDto.getPageIndex())
                                .chapter(pageDto.getChapter())
                                        .book(bookService.findById(pageDto.getBookId()))
                                                .id(pageDto.getId())
                                                        .content(pageDto.getContent()).build();
        pageBookService.update(page);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        pageBookService.delete(id);
    }
}
