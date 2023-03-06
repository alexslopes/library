package br.com.cabidiomas.library.book.page.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageBookDto {

    private Long id;
    private Long bookId;
    private Integer chapter;
    private Long pageIndex;
    private String content;
}
