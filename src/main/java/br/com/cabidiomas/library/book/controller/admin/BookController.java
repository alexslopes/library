package br.com.cabidiomas.library.book.controller.admin;

import br.com.cabidiomas.library.book.controller.BookDto;
import br.com.cabidiomas.library.book.controller.BookMapper;
import br.com.cabidiomas.library.language.service.LanguageService;
import br.com.cabidiomas.library.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/book")
@Secured("ROLE_ADMIN")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final LanguageService languageService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto save(@RequestBody BookDto bookDto){
        var language = languageService.findByIdentification(bookDto.getLanguageIdentification());
        var module = BookMapper.dtoToEntity(bookDto, language);
        var dto = bookService.save(module);
        return BookMapper.entityToDto(dto);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateModule( @PathVariable Integer id, @RequestBody BookDto dto){
        var language = languageService.findByIdentification(dto.getLanguageIdentification());
        var module = BookMapper.dtoToEntity(dto, language);
        bookService.updateModule(id, module);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        bookService.delete(id);
    }

    @GetMapping("obter-modulo-por-idioma/{id}")
    public List<BookDto> getAllModulesByLanguage(@PathVariable Integer id){
        var modules = bookService.findModuleByLanguage(id);
        return modules.stream().map(BookMapper::entityToDto).collect(Collectors.toList());
    }
}
