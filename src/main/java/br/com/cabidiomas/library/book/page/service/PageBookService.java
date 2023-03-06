package br.com.cabidiomas.library.book.page.service;

import br.com.cabidiomas.library.book.page.model.PageBook;
import br.com.cabidiomas.library.book.page.model.PageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PageBookService {

    private final PageRepository pageRepository;

    public void save(PageBook page) {
        this.pageRepository.save(page);
    }

    public PageBook findPageBookById(Long id) {
        return this.pageRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Página não encontrada"));
    }

    public void update(PageBook pageBook) {
        boolean isExiste = pageRepository.existsById(pageBook.getId());
        if(isExiste){
            this.save(pageBook);
        }
    }

    public void delete(Long id) {
        this.pageRepository.deleteById(id);
    }
}
