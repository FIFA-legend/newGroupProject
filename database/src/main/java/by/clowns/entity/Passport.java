package by.clowns.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Past;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Embeddable
public class Passport {

    @Column(name = "name")
    @NotEmpty(message = "errors.user.realName")
    private String name;

    @Column(name = "surname")
    @NotEmpty(message = "errors.user.realSurname")
    private String surname;

    @Column(name = "passport_id")
    @NotEmpty(message = "errors.user.passportId")
    private String passportId;

    @Column(name = "birth")
    @Past(message = "errors.user.birth")
    private Date birth;

}