package by.clowns.service;

import by.clowns.entity.Truck;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface TruckService {

    void create(Truck entity);

    Set<Truck> read();

    void update(Truck entity);

    void delete(long id);

    Truck get(long id);

    Set<Truck> getUnselectedTrucks();

}
