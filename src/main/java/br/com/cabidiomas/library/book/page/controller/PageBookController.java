package br.com.cabidiomas.library.book.page.controller;

import br.com.cabidiomas.library.book.page.service.PageBookService;
import br.com.cabidiomas.library.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/obter-páginas-por-capítulo/{idBook}/{chapter}")
    public Page<PageBookDto> getPageBook(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "1") Integer pageSize,
            @RequestParam(value = "sort", defaultValue = "pageIndex") String sort,
            @PathVariable Long idBook,
            @PathVariable Integer chapter
    ){
        var pageBook =  pageBookService.findAllPagesByChapter(idBook, chapter, page, pageSize, sort);

        List<PageBookDto> pageBookDtos = pageBook.get().map(PageBookMapper::entityToDto).collect(Collectors.toList());

        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC,sort));

        return new PageImpl<>(
                pageBookDtos,
                pageRequest, pageBook.getTotalElements());
    }

}
