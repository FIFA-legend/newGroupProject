package by.clowns.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "taxis")
public class Taxi extends Car {

    @Enumerated(EnumType.STRING)
    @Column(name = "comfort")
    private Comfort comfort;

    public Taxi(String brand, double price, String number, Comfort comfort) {
        super(brand, price, number);
        this.comfort = comfort;
    }
}