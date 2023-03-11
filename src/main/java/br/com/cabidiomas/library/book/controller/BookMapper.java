package br.com.cabidiomas.library.book.controller;

import br.com.cabidiomas.library.book.model.Book;

import java.util.List;

public class BookMapper {

    public static BookDetailDto entityToDto(Book entity, List<Integer> chapters) {
        if(entity == null){
            return null;
        }

        return BookDetailDto.builder()
                .id(entity.getId())
                .idLevel(entity.getLevel().getId())
                .pageQuantity(entity.getPageBooks().size())
                .chapters(chapters)
                .build();
    }

}
