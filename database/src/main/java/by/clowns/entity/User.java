package by.clowns.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "username")
    @NotEmpty(message = "errors.user.username.empty")
    @Size(min = 3, max = 16, message = "errors.user.username.size")
    private String username;

    @Column(name = "password")
    @NotEmpty(message = "errors.user.password.empty")
    @Size(min = 8, max = 24, message = "errors.user.password.size")
    private String password;

    private Passport passport;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    @NotNull(message = "errors.user.role")
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
