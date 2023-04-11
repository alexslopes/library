package br.com.cabidiomas.library.module.page.service;

import br.com.cabidiomas.library.module.level.service.LevelService;
import br.com.cabidiomas.library.module.page.controller.PageBookDto;
import br.com.cabidiomas.library.module.page.controller.PageBookMapper;
import br.com.cabidiomas.library.module.page.model.PageBook;
import br.com.cabidiomas.library.module.page.model.PageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PageBookService {

    private final PageRepository pageRepository;
    private final LevelService levelService;

    public PageBook save(PageBookDto pageBookDto) {
        var level = levelService.findLevelById(pageBookDto.getLevelId());
        var page = PageBookMapper.dtoToEntity(pageBookDto);
        page.setLevel(level);
        return this.pageRepository.save(page);
    }

    public PageBook findPageBookById(Long id) {
        return this.pageRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Página não encontrada"));
    }

    public void update(PageBookDto pageBookDto) {
        var pageBook = PageBookMapper.dtoToEntity(pageBookDto);
        boolean isExiste = pageRepository.existsById(pageBook.getId());
        if(isExiste){
            this.pageRepository.save(pageBook);
        }
    }

    public void delete(Long id) {
        this.pageRepository.deleteById(id);
    }

    public Page<PageBook> findAllPagesByChapter(Long idBook, Integer chapter, Integer page, Integer pageSize, String sortColumn) {
        Sort sort =  Sort.by(Sort.Direction.ASC, sortColumn);
        PageRequest pageRequest = PageRequest.of(page, pageSize, sort);

        return pageRepository.findAllByLevelIdAndChapter(pageRequest, idBook, chapter);
    }

    public PageBook findPageBookByChapter(Integer chapter) {
        return pageRepository.findPageBookByChapter(chapter).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Página não encontrada"));
    }
}
