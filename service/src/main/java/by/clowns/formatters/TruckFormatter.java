package by.clowns.formatters;

import by.clowns.entity.Truck;
import by.clowns.repository.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Locale;

@Service
public class TruckFormatter implements Formatter<Truck> {

    private final TruckRepository truckRepository;

    @Autowired
    public TruckFormatter(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    @Override
    public Truck parse(String s, Locale locale) throws ParseException {
        return truckRepository.findById(Long.parseLong(s));
    }

    @Override
    public String print(Truck truck, Locale locale) {
        return truck.getId().toString();
    }
}
