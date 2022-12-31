package br.com.cabidiomas.library.material.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.servlet.http.Part;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaterialDto {

    private Long id;
    private Integer idBook;
    private String description;
    private Part file;
}
