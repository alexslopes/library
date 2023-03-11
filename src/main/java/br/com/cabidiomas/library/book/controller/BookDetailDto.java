package br.com.cabidiomas.library.book.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDetailDto {

    private Long id;
    private Integer idLevel;
    private List<Integer> chapters;
    private Integer pageQuantity;
}
