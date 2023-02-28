package br.com.cabidiomas.library.level.controller.admin;

import br.com.cabidiomas.library.book.controller.BookDto;
import br.com.cabidiomas.library.book.model.Book;
import br.com.cabidiomas.library.book.service.BookService;
import br.com.cabidiomas.library.level.controller.LevelDto;
import br.com.cabidiomas.library.level.controller.LevelMapper;
import br.com.cabidiomas.library.language.service.LanguageService;
import br.com.cabidiomas.library.level.service.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/level")
@Secured("ROLE_ADMIN")
@RequiredArgsConstructor
public class LevelAdminController {

    private final LevelService levelService;
    private final LanguageService languageService;

    private final BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto save(@RequestBody BookDto bookDto){
        var language = languageService.findById(bookDto.getLevel().getLanguageId());
        var level = LevelMapper.dtoToEntity(bookDto.getLevel(), language);
        var dto = levelService.save(level);

        var book = Book.builder().id(bookDto.getId()).
            description(bookDto.getDescription()).
            level(level).
            content(bookDto.getContent()).build();

        this.bookService.save(book);

        return BookDto.builder().id(book.getId()).level(LevelMapper.entityToDto(level)).build();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateModule( @PathVariable Integer id, @RequestBody BookDto bookDto){
        var language = languageService.findById(bookDto.getLevel().getLanguageId());
        var module = LevelMapper.dtoToEntity(bookDto.getLevel(), language);
        levelService.updateModule(id, module);

        var book = Book.builder().id(bookDto.getId()).
                description(bookDto.getDescription()).
                level(module).
                content(bookDto.getContent()).build();

        this.bookService.save(book);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        levelService.delete(id);
    }

    @GetMapping("/obter-modulo-por-idioma/{id}")
    public Page<LevelDto> getSLevels(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sort", defaultValue = "description") String sort,
            @PathVariable Integer id
    ){
        var levelPage =  levelService.findAllLevelsByLanguage(page, pageSize, sort, id);

        List<LevelDto> levelDtos = levelPage.get().map(LevelMapper::entityToDto).collect(Collectors.toList());

        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC,sort));

        return new PageImpl<>(
                levelDtos,
                pageRequest, levelPage.getTotalElements());
    }
}
