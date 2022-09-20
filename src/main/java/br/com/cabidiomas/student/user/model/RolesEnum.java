package br.com.cabidiomas.student.user.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RolesEnum {
    ROLE_ADMIN(1),
    ROLE_STUDENT(2);

    private final Integer id;

    public static final String ADMIN_VALUE = "ROLE_ADMIN";
    public static final String STUDENT_VALUE = "ROLE_STUDENT";
    public static RolesEnum getRoleById(Integer id){
        switch (id){
            case 1:
                return ROLE_ADMIN;
            case 2:
                return ROLE_STUDENT;
            default:
                return null;
        }
    }


}
