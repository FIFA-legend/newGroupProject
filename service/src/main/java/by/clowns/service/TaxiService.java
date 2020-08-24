package by.clowns.service;

import by.clowns.entity.Comfort;
import by.clowns.entity.Taxi;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface TaxiService {

    void create(Taxi entity);

    Set<Taxi> read();

    void update(Taxi entity);

    void delete(long id);

    Taxi get(long id);

    Comfort[] getAllComforts();

    Set<Taxi> getUnselectedTaxis();

}
