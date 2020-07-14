package by.clowns.dto;

import by.clowns.entity.Region;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {

    private String brand;

    private String number;

    private double minPrice;

    private double maxPrice;

    private List<Region> regions;
}
