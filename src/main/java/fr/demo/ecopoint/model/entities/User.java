package fr.demo.ecopoint.model.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "login"))
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    Long id;

    @Getter
    @Setter
    String login;

    @Getter
    @Setter
    String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "users_roles",
        joinColumns = @JoinColumn(
            name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(
            name = "role_id", referencedColumnName = "id"))
    @Getter
    @Setter
    Collection<Role> roles;


    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login, String password, Collection <Role> roles) {
        this.login = login;
        this.password = password;
        this.roles = roles;
    }
}