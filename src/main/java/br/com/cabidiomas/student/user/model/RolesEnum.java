package br.com.cabidiomas.student.user.model;

import lombok.Getter;

@Getter
public enum RolesEnum {
    ADMIN(1),
    STUDENT(2);

    private final Integer id;

    RolesEnum(Integer id) {
        this.id = id;
    }

    public static RolesEnum getRoleById(Integer id){
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
