package by.clowns.service;

import by.clowns.dao.Dao;
import by.clowns.dao.TaxiRepository;
import by.clowns.entity.Taxi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TaxiService implements ServiceInterface<Taxi> {

    private final TaxiRepository taxiRepository;

    @Autowired
    public TaxiService(TaxiRepository taxiRepository) {
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
    public void update(Taxi entity, long id) {
        Taxi taxiToChange = taxiRepository.findById(id);
        taxiToChange.setComfort(entity.getComfort());
        taxiToChange.setNumber(entity.getNumber());
        taxiToChange.setPrice(entity.getPrice());
        taxiToChange.setRegions(entity.getRegions());
        taxiToChange.setRequest(entity.getRequest());
        taxiRepository.save(taxiToChange);
    }

    @Override
    public void delete(long id) {
        taxiRepository.deleteById(id);
    }

    @Override
    public Taxi get(long id) {
        return taxiRepository.findById(id);
    }
}
