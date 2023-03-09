package br.com.cabidiomas.library.book.page.controller;

import br.com.cabidiomas.library.book.model.Book;
import br.com.cabidiomas.library.book.page.model.PageBook;
import org.springframework.data.domain.Page;

import java.util.List;

public class PageBookMapper {

    public static PageBook dtoToEntity(PageBookDto dto, Book book){
        if(dto == null){
            return null;
        }

        return PageBook.builder()
                .id(dto.getId())
                .book(book)
                .chapter(dto.getChapter())
                .pageIndex(dto.getPageIndex())
                .content(dto.getContent())
                .build();
    }

    public static PageBookDto entityToDto(PageBook entity) {
        if(entity == null) {
            return null;
        }

        return PageBookDto.builder()
                .id(entity.getId())
                .bookId(entity.getBook().getId())
                .chapter(entity.getChapter())
                .pageIndex(entity.getPageIndex())
                .content(entity.getContent()).build();
    }

    public static ChapterPageBookDto entityToDto(Page<PageBookDto> page, List<Integer> chapters) {

        return ChapterPageBookDto.builder()
                .chapters(chapters)
                .pages(page).build();
    }

}
