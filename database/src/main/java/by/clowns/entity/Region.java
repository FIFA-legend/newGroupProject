package by.clowns.entity;

import by.clowns.validation.ValidRegions;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "regions")
public class Region extends BaseEntity {

    @Column(name = "name")
    @ValidRegions(
            allowedRegionNames = {"Советский", "Центральный", "Первомайский", "Фрунзенский", "Партизанский", "Ленинский", "Октябрьский"}
            )
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "regions")
    private Set<Car> cars;

    @Version
    @Column(name = "version")
    private Long version;
}
