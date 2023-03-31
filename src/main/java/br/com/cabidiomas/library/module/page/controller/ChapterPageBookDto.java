package br.com.cabidiomas.library.module.page.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChapterPageBookDto {

    private List<Integer> chapters;
    private Page<PageBookDto> pages;
}
