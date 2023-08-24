package br.com.cabidiomas.library.user.model;

import br.com.cabidiomas.library.module.level.model.StudentLevel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(unique = true, name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="usuario_role",
            joinColumns=@JoinColumn(name="usuario_id"),
            inverseJoinColumns=@JoinColumn(name="role_id")
    )
    private List<Role> roles;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private List<StudentLevel> studentLevels;

    public Usuario(Usuario user) {
        super();
        this.name = user.getName();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.roles = user.getRoles();
        this.id = user.getId();
    }

}
