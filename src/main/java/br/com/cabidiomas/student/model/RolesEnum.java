package br.com.cabidiomas.student.model;

import lombok.Getter;

@Getter
public enum RolesEnum {
    ADMIN(1L),
    STUDENT(2L);

    public final Long id;

    RolesEnum(Long id) {
        this.id = id;
    }
}
