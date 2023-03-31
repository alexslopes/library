package br.com.cabidiomas.library.module.page.controller;


import br.com.cabidiomas.library.module.page.service.PageBookService;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody PageBookDto pageDto) {
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody PageBookDto pageDto) {
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        pageBookService.delete(id);
    }
}
