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

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    private Passport passport;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Request> requests;

    @Version
    @Column(name = "version")
    private Long version;

    public User(String name, String surname, Passport passport, Role role) {
        this.name = name;
        this.surname = surname;
        this.passport = passport;
        this.role = role;
    }
}
