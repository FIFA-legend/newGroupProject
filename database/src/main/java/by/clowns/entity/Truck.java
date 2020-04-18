package by.clowns.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "trucks")
public class Truck extends Car {

    @Column(name = "carrying")
    private int carrying;

    public Truck(double price, String number, int carrying) {
        super(price, number);
        this.carrying = carrying;
    }
}