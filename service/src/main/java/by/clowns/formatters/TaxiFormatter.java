package by.clowns.formatters;

import by.clowns.entity.Taxi;
import by.clowns.repository.TaxiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Locale;

@Service
public class TaxiFormatter implements Formatter<Taxi> {

    private final TaxiRepository taxiRepository;

    @Autowired
    public TaxiFormatter(TaxiRepository taxiRepository) {
        this.taxiRepository = taxiRepository;
    }

    @Override
    public Taxi parse(String s, Locale locale) throws ParseException {
        return taxiRepository.findById(Long.parseLong(s));
    }

    @Override
    public String print(Taxi taxi, Locale locale) {
        return taxi.getId().toString();
    }
}
