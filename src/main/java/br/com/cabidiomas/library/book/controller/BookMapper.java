package br.com.cabidiomas.library.book.controller;

import br.com.cabidiomas.library.book.model.Book;
import br.com.cabidiomas.library.language.model.Language;

public class BookMapper {

    public static Book dtoToEntity(BookDto dto, Language language){
        if(dto == null){
            return null;
        }

        return Book.builder()
                .id(dto.getId())
                .description(dto.getDescription())
                .language(language)
                .build();
    }

    public static BookDto entityToDto(Book entity) {
        if(entity == null) {
            return null;
        }

        return BookDto.builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .languageIdentification(entity.getLanguage().getIdentification()).build();
    }
}
