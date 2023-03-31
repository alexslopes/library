package br.com.cabidiomas.library.module.level.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LevelDto {

    private Integer id;
    private String description;
    private Integer languageId;
}
