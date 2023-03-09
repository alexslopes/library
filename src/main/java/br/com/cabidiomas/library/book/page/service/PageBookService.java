package br.com.cabidiomas.library.book.page.service;

import br.com.cabidiomas.library.book.page.model.PageBook;
import br.com.cabidiomas.library.book.page.model.PageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
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

    public Page<PageBook> findAllPagesByChapter(Long idBook, Integer chapter, Integer page, Integer pageSize, String sortColumn) {
        Sort sort =  Sort.by(Sort.Direction.ASC, sortColumn);
        PageRequest pageRequest = PageRequest.of(page, pageSize, sort);

        return pageRepository.findAllByBookIdAndChapter(pageRequest, idBook, chapter);
    }

    public List<Integer> getAllChapterFromBook(Long idBook) {
        return pageRepository.findAllChapterByBook(idBook);
    }

    public PageBook findPageBookByChapter(Integer chapter) {
        return pageRepository.findPageBookByChapter(chapter).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Página não encontrada"));
    }
}
