package br.com.cabidiomas.library.material.controller;

import br.com.cabidiomas.library.book.model.Book;
import br.com.cabidiomas.library.book.service.BookService;
import br.com.cabidiomas.library.material.model.Material;
import br.com.cabidiomas.library.material.service.MaterialService;
import br.com.cabidiomas.library.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin/material")
@Secured("ROLE_ADMIN")
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialService materialService;
    private final BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void save(@RequestParam("idBook") Integer idBook,
                     @RequestParam("id") Long id,
                     @RequestParam("description") String description,
                     @RequestParam("file") Part file) throws IOException {
        Book book = bookService.findBookById(idBook);
        var material = Material.builder().id(id).
                description(description).
                book(book).
                file(FileUtils.partToBytes(file)).build();
        materialService.save(material);
    }

    @GetMapping("{id}")
    public List<Material> list(@PathVariable Integer id){
        return materialService.findAllByBookId(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        materialService.delete(id);
    }

}
