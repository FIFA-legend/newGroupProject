package by.clowns.service.serviceImpl;

import by.clowns.entity.Comfort;
import by.clowns.repository.TaxiRepository;
import by.clowns.entity.Taxi;
import by.clowns.service.TaxiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class TaxiServiceImpl implements TaxiService {

    private final TaxiRepository taxiRepository;

    @Autowired
    public TaxiServiceImpl(TaxiRepository taxiRepository) {
        this.taxiRepository = taxiRepository;
    }

    @Override
    public void create(Taxi entity) {
        taxiRepository.save(entity);
    }

    @Override
    public Set<Taxi> read() {
        List<Taxi> taxiList = taxiRepository.findAll();
        return new HashSet<>(taxiList);
    }

    @Override
    public void update(Taxi entity) {
        taxiRepository.save(entity);
    }

    @Override
    public void delete(long id) {
        taxiRepository.deleteById(id);
    }

    @Override
    public Taxi get(long id) {
        return taxiRepository.findById(id);
    }

    @Override
    public Comfort[] getAllComforts() {
        return Comfort.values();
    }

    @Override
    public Set<Taxi> getUnselectedTaxis() {
        List<Taxi> taxis = taxiRepository.findAll();
        Date date = new Date(System.currentTimeMillis());
        Set<Taxi> taxisToReturn = new HashSet<>();
        for (Taxi taxi : taxis) {
            if (taxi.getRentTime().before(date)) taxisToReturn.add(taxi);
        }
        return taxisToReturn;
    }
}
