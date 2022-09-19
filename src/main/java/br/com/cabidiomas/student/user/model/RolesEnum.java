package br.com.cabidiomas.student.user.model;

import lombok.Getter;

@Getter
public enum RolesEnum {
    ADMIN(1L),
    STUDENT(2L);

    private final Long id;

    RolesEnum(Long id) {
        this.id = id;
    }

    public static RolesEnum getRoleByName(Integer id){
        switch (id){
            case 1:
                return ADMIN;
            case 2:
                return STUDENT;
            default:
                return null;
        }
    }
}
