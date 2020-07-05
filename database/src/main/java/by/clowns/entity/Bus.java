package by.clowns.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "buses")
public class Bus extends Car {

    @Column(name = "capacity")
    @Min(value = 21, message = "errors.bus.capacity")
    @Max(value = 100, message = "errors.bus.capacity")
    private int capacity;

    public Bus(String brand, double price, String number, int capacity) {
        super(brand, price, number);
        this.capacity = capacity;
    }
}
