package br.com.cabidiomas.library.level.controller;

import br.com.cabidiomas.library.level.model.Level;
import br.com.cabidiomas.library.language.model.Language;

public class LevelMapper {

    public static Level dtoToEntity(LevelDto dto, Language language){
        if(dto == null){
            return null;
        }

        return Level.builder()
                .id(dto.getId())
                .description(dto.getDescription())
                .language(language)
                .build();
    }

    public static LevelDto entityToDto(Level entity) {
        if(entity == null) {
            return null;
        }

        return LevelDto.builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .languageId(entity.getLanguage().getId()).build();
    }
}
