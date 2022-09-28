package br.com.cabidiomas.library.materials.controller.admin.module;

import br.com.cabidiomas.library.materials.model.Language;
import br.com.cabidiomas.library.materials.model.Module;

public class ModuleMapper {

    public static Module dtoToEntity(ModuleDto dto, Language language){
        if(dto == null){
            return null;
        }

        return Module.builder()
                .id(dto.getId())
                .description(dto.getDescription())
                .language(language)
                .build();
    }

    public static ModuleDto entityToDto(Module entity) {
        if(entity == null) {
            return null;
        }

        return ModuleDto.builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .languageIdentification(entity.getLanguage().getIdentification()).build();
    }
}
