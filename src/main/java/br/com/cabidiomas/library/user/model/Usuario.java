package br.com.cabidiomas.library.user.model;

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
    @JoinTable(name="user_role",
            joinColumns=@JoinColumn(name="usuario_id"),
            inverseJoinColumns=@JoinColumn(name="role_id")
    )
    private List<Role> roles;

    public Usuario(Usuario user) {
        super();
        this.name = user.getName();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.roles = user.getRoles();
        this.id = user.getId();
    }

}
