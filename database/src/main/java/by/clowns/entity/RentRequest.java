package by.clowns.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "requests")
public class RentRequest extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private User user;

    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @Version
    @Column(name = "version")
    private Long version;

    public RentRequest(User user, Car car) {
        this.user = user;
        this.car = car;
    }
}
