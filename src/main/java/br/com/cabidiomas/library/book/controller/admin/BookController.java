package br.com.cabidiomas.library.book.controller.admin;

import br.com.cabidiomas.library.book.controller.BookDto;
import br.com.cabidiomas.library.book.controller.BookMapper;
import br.com.cabidiomas.library.language.service.LanguageService;
import br.com.cabidiomas.library.book.service.BookService;
import br.com.cabidiomas.library.user.controller.dto.UsuarioDto;
import br.com.cabidiomas.library.user.controller.dto.UsuarioMapper;
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
@RequestMapping("/api/admin/book")
@Secured("ROLE_ADMIN")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final LanguageService languageService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto save(@RequestBody BookDto bookDto){
        var language = languageService.findById(bookDto.getLanguageId());
        var module = BookMapper.dtoToEntity(bookDto, language);
        var dto = bookService.save(module);
        return BookMapper.entityToDto(dto);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateModule( @PathVariable Integer id, @RequestBody BookDto dto){
        var language = languageService.findById(dto.getLanguageId());
        var module = BookMapper.dtoToEntity(dto, language);
        bookService.updateModule(id, module);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        bookService.delete(id);
    }

    @GetMapping("/obter-modulo-por-idioma/{id}")
    public Page<BookDto> getSBooks(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sort", defaultValue = "description") String sort,
            @PathVariable Integer id
    ){
        var bookPage =  bookService.findAllBooksByLanguage(page, pageSize, sort, id);

        List<BookDto> bookDtos = bookPage.get().map(BookMapper::entityToDto).collect(Collectors.toList());

        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC,sort));

        return new PageImpl<>(
                bookDtos,
                pageRequest, bookPage.getTotalElements());
    }
}
