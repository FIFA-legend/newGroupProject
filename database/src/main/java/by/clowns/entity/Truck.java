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
@Table(name = "trucks")
public class Truck extends Car {

    @Column(name = "carrying")
    @Min(value = 1000, message = "errors.truck.carrying")
    @Max(value = 5000, message = "errors.truck.carrying")
    private int carrying;

    public Truck(String brand, double price, String number, int carrying) {
        super(brand, price, number);
        this.carrying = carrying;
    }
}