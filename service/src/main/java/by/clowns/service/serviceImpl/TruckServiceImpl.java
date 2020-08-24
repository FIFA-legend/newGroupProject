package by.clowns.service.serviceImpl;

import by.clowns.repository.TruckRepository;
import by.clowns.entity.Truck;
import by.clowns.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class TruckServiceImpl implements TruckService {

    private final TruckRepository truckRepository;

    @Autowired
    public TruckServiceImpl(TruckRepository truckRepository) {
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
    public void update(Truck entity) {
        truckRepository.save(entity);
    }

    @Override
    public void delete(long id) {
        truckRepository.deleteById(id);
    }

    @Override
    public Truck get(long id) {
        return truckRepository.findById(id);
    }

    @Override
    public Set<Truck> getUnselectedTrucks() {
        List<Truck> trucks = truckRepository.findAll();
        Date date = new Date(System.currentTimeMillis());
        Set<Truck> trucksToReturn = new HashSet<>();
        for (Truck truck : trucks) {
            if (truck.getRentTime().before(date)) trucksToReturn.add(truck);
        }
        return trucksToReturn;
    }
}
