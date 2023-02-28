package br.com.cabidiomas.library.level.controller;

import br.com.cabidiomas.library.book.controller.BookDto;
import br.com.cabidiomas.library.book.model.Book;
import br.com.cabidiomas.library.book.service.BookService;
import br.com.cabidiomas.library.level.service.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/level")
@RequiredArgsConstructor
public class LevelController {

    private final LevelService levelService;
    private final BookService bookService;

    @GetMapping("{id}")
    public BookDto findLevelById(@PathVariable Integer id){
        //LevelMapper.entityToDto(levelService.findLevelById(id));
         var book = bookService.findById(Long.valueOf(String.valueOf(id)));
         return BookDto.builder().id(book.getId()).description(book.getDescription()).level(LevelMapper.entityToDto(book.getLevel())).content(book.getContent()).build();
    }
}
