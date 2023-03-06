package br.com.cabidiomas.library.book.page.controller;

import br.com.cabidiomas.library.book.page.service.PageBookService;
import br.com.cabidiomas.library.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/page")
@RequiredArgsConstructor
public class PageBookController {

    private final PageBookService pageBookService;
    private final BookService bookService;

    @GetMapping("{id}")
    public PageBookDto findPageBookById(@PathVariable Long id) {
        var page = pageBookService.findPageBookById(id);
        return PageBookDto.builder()
                .id(page.getId())
                .bookId(page.getBook().getId())
                .chapter(page.getChapter())
                .pageIndex(page.getPageIndex())
                .content(page.getContent()).build();

    }

}
