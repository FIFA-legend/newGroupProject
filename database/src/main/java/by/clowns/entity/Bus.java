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
@Table(name = "buses")
public class Bus extends Car {

    @Column(name = "capacity")
    private int capacity;

    public Bus(double price, String number, int capacity) {
        super(price, number);
        this.capacity = capacity;
    }
}
