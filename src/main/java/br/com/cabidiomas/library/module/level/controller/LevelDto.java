package br.com.cabidiomas.library.module.level.controller;

import br.com.cabidiomas.library.module.book.controller.BookDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LevelDto {

    private Integer id;
    private String description;
    private Integer languageId;
}
