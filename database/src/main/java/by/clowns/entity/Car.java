package by.clowns.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
@Table(name = "cars")
@Inheritance(strategy = InheritanceType.JOINED)
public class Car extends BaseEntity {

    @Column(name = "brand")
    @NotEmpty(message = "errors.car.brand")
    private String brand;

    @Column(name = "model")
    @NotEmpty(message = "errors.car.model")
    private String model;

    @Column(name = "price")
    @Min(value = 2, message = "errors.car.price")
    @Max(value = 50, message = "errors.car.price")
    private double price;

    @Column(name = "rent_time")
    private Date rentTime;

    @Column(name = "number")
    @Pattern(regexp = "^\\d{4}\\s[A-Z]{2}-\\d$", message = "errors.car.number")
    private String number;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "regions_cars",
            inverseJoinColumns = @JoinColumn(name = "region_id"),
            joinColumns = @JoinColumn(name = "car_id")
    )
    private Set<Region> regions;

    @OneToOne(mappedBy = "car", fetch = FetchType.EAGER)
    private RentRequest request;

    @Version
    @Column(name = "version")
    private Long version;

    public Car(String brand, double price, String number) {
        this.brand = brand;
        this.price = price;
        this.number = number;
    }
}