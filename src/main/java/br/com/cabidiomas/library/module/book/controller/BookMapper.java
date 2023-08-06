package br.com.cabidiomas.library.module.book.controller;

import br.com.cabidiomas.library.module.level.model.Level;
import br.com.cabidiomas.library.module.book.model.Book;

public class BookMapper {

    public static Book dtoToEntity(BookDto dto){
        if(dto == null){
            return null;
        }

        return Book.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .build();
    }

    public static Book dtoToEntity(BookDto dto, Level level){
        if(dto == null){
            return null;
        }

        return Book.builder()
                .id(dto.getId())
                .level(level)
                .content(dto.getContent())
                .build();
    }

    public static BookDto entityToDto(Book entity) {
        if(entity == null) {
            return null;
        }

        return BookDto.builder()
                .id(entity.getId())
                .levelId(entity.getLevel().getId())
                .content(entity.getContent()).build();
    }


}
