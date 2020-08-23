package by.clowns.service;

import by.clowns.repository.BusRepository;
import by.clowns.entity.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BusService implements ServiceInterface<Bus> {

    private final BusRepository busRepository;

    @Autowired
    public BusService(BusRepository busRepository) {
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
    public void update(Bus entity, long id) {
        Bus busToUpdate = busRepository.findById(id);
        busToUpdate.setCapacity(entity.getCapacity());
        busToUpdate.setNumber(entity.getNumber());
        busToUpdate.setPrice(entity.getPrice());
        busToUpdate.setRegions(entity.getRegions());
        busToUpdate.setRequest(entity.getRequest());
        busRepository.save(busToUpdate);
    }

    @Override
    public void delete(long id) {
        busRepository.deleteById(id);
    }

    @Override
    public Bus get(long id) {
        return busRepository.findById(id);
    }

    public Set<Bus> getFreeBuses() {
        List<Bus> buses = busRepository.findAll();
        Set<Bus> busesToReturn = new HashSet<>();
        Date date = new Date(System.currentTimeMillis());
        for (Bus bus : buses) {
            if (bus.getRentTime().before(date)) busesToReturn.add(bus);
        }
        return busesToReturn;
    }
}
