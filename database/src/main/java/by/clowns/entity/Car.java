package by.clowns.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
@Table(name = "cars")
public class Car extends BaseEntity {

    @Column(name = "price")
    private double price;

    @Column(name = "number")
    private String number;

    @ManyToMany(mappedBy = "cars")
    private Set<Region> regions;

    public Car(double price, String number) {
        this.price = price;
        this.number = number;
    }
}