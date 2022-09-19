package br.com.cabidiomas.student.user.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDto {

    private Integer id;
    private String name;
    private String login;
    private String password;
    @Builder.Default
    private Long roleId = 2L;
}
