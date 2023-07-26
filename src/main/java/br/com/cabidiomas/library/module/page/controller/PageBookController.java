package br.com.cabidiomas.library.module.page.controller;

import br.com.cabidiomas.library.module.page.service.PageBookService;
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

    @GetMapping("{id}")
    public PageBookDto findPageBookById(@PathVariable Long id) {
        return this.pageBookService.findPageBookById(id);
    }

    @GetMapping("/obter-livro-por-level/{idLevel}")
    public PageBookDto getPagesByChapter(
            @PathVariable Integer idLevel
    ){
        return pageBookService.findPageBookByLevel(idLevel);
    }

    @GetMapping("/obter-paginas/{idBook}/{chapter}")
    public Page<PageBookDto> getPagesByChapter(
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

//    @GetMapping("/obter-paginas-por-capitulo/{idBook}/{chapter}")
//    public ChapterPageBookDto getPageBook(
//            @RequestParam(value = "page", defaultValue = "0") Integer page,
//            @RequestParam(value = "size", defaultValue = "1") Integer pageSize,
//            @RequestParam(value = "sort", defaultValue = "pageIndex") String sort,
//            @PathVariable Long idBook,
//            @PathVariable Integer chapter
//    ){
//
////        var page = pageBookService.findPageBookByChapter(chapter);
//        var pageBookList =  pageBookService.getAllChapterFromBook(idBook);
//
//        var pageBook =  pageBookService.findAllPagesByChapter(idBook, chapter, page, pageSize, sort);
//        List<PageBookDto> pageBookDtos = pageBook.get().map(PageBookMapper::entityToDto).collect(Collectors.toList());
//
//        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC,sort));
//
//        var pagesBookList = new PageImpl<>(
//                pageBookDtos,
//                pageRequest, pageBook.getTotalElements());
//
//        return PageBookMapper.entityToDto(pagesBookList, pageBookList);
//    }

}
