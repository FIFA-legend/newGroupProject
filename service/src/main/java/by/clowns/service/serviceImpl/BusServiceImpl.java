package by.clowns.service.serviceImpl;

import by.clowns.repository.BusRepository;
import by.clowns.entity.Bus;
import by.clowns.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class BusServiceImpl implements BusService {

    private final BusRepository busRepository;

    @Autowired
    public BusServiceImpl(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    @Override
    public void create(Bus entity) {
        busRepository.save(entity);
    }

    @Override
    public Set<Bus> read() {
        List<Bus> busList = busRepository.findAll();
        return new HashSet<>(busList);
    }

    @Override
    public void update(Bus entity) {
        busRepository.save(entity);
    }

    @Override
    public void delete(long id) {
        busRepository.deleteById(id);
    }

    @Override
    public Bus get(long id) {
        return busRepository.findById(id);
    }

    @Override
    public Set<Bus> getUnselectedBuses() {
        List<Bus> buses = busRepository.findAll();
        Set<Bus> busesToReturn = new HashSet<>();
        Date date = new Date(System.currentTimeMillis());
        for (Bus bus : buses) {
            if (bus.getRentTime().before(date)) busesToReturn.add(bus);
        }
        return busesToReturn;
    }
}
