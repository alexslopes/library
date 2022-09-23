package br.com.cabidiomas.library.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    private Integer id;
    private String name;

    @Override
    public String getAuthority() {
        return  this.name;
    }

    public static Role getRolebyId(Integer id){
        var roleEnum = RolesEnum.getRoleById(id);
        return Role.builder()
                .id(roleEnum.getId())
                .name(roleEnum.name()).build();
    }
}
