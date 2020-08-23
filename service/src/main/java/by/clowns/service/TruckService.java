package by.clowns.service;

import by.clowns.repository.TruckRepository;
import by.clowns.entity.Truck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TruckService implements ServiceInterface<Truck> {

    private final TruckRepository truckRepository;

    @Autowired
    public TruckService(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    @Override
    public void create(Truck entity) {
        truckRepository.save(entity);
    }

    @Override
    public Set<Truck> read() {
        List<Truck> truckList = truckRepository.findAll();
        return new HashSet<>(truckList);
    }

    @Override
    public void update(Truck entity, long id) {
        Truck truckToChange = truckRepository.findById(id);
        truckToChange.setCarrying(entity.getCarrying());
        truckToChange.setNumber(entity.getNumber());
        truckToChange.setPrice(entity.getPrice());
        truckToChange.setRegions(entity.getRegions());
        truckToChange.setRequest(entity.getRequest());
        truckRepository.save(truckToChange);
    }

    @Override
    public void delete(long id) {
        truckRepository.deleteById(id);
    }

    @Override
    public Truck get(long id) {
        return truckRepository.findById(id);
    }

    public Set<Truck> getFreeTrucks() {
        List<Truck> trucks = truckRepository.findAll();
        Date date = new Date(System.currentTimeMillis());
        Set<Truck> trucksToReturn = new HashSet<>();
        for (Truck truck : trucks) {
            if (truck.getRentTime().before(date)) trucksToReturn.add(truck);
        }
        return trucksToReturn;
    }
}
