package br.com.cabidiomas.library.materials.controller.admin.module;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModuleDto {

    private Integer id;
    private String description;
    private String languageIdentification;
}
