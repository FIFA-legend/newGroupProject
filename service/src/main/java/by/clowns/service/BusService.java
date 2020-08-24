package by.clowns.service;

import by.clowns.entity.Bus;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface BusService {

    void create(Bus entity);

    Set<Bus> read();

    void update(Bus entity);

    void delete(long id);

    Bus get(long id);

    Set<Bus> getUnselectedBuses();
}
