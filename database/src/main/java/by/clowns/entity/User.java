package by.clowns.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    private Passport passport;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Request> requests;

    @Version
    @Column(name = "version")
    private Long version;

    public User(String username, String password, Passport passport, Role role) {
        this.username = username;
        this.password = password;
        this.passport = passport;
        this.role = role;
    }

    public User(String username, String password, Passport passport, Role role, Set<Request> requests) {
        this.username = username;
        this.password = password;
        this.passport = passport;
        this.role = role;
        this.requests = requests;
    }
}
