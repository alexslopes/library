package br.com.cabidiomas.library.book.controller;

import br.com.cabidiomas.library.level.controller.LevelDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.Part;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private Long id;
    private LevelDto level;
    private String description;
    private String content;
}
